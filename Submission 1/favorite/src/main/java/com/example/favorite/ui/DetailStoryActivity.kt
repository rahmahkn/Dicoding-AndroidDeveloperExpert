package com.example.favorite.ui

import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.core.domain.model.Story
import com.example.favorite.R
import com.example.favorite.databinding.ActivityDetailStoryBinding
import com.example.favorite.di.favoritedViewModelModule
import com.example.favorite.viewmodel.FavoritedViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class DetailStoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailStoryBinding
    private lateinit var ivPhoto: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvTime: TextView
    private lateinit var ivFav: ImageView
    private lateinit var favStory: Story

    private lateinit var dataId: String
    private lateinit var dataImage: String
    private lateinit var dataName: String
    private lateinit var dataDesc: String
    private lateinit var dataTime: String

    private val favoritedViewModel: FavoritedViewModel by viewModel()
    private var job: Job = Job()
    private var updateJob: Job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoritedViewModelModule)

        ivPhoto = binding.detailPhoto
        tvName = binding.detailName
        tvDescription = binding.detailDescription
        tvTime = binding.detailTime
        ivFav = binding.detailFavorite

        dataId = intent.getStringExtra("mId")!!
        dataImage = intent.getStringExtra("mPhotoUrl")!!
        dataName = intent.getStringExtra("mName")!!
        dataDesc = intent.getStringExtra("mDescription")!!
        dataTime = intent.getStringExtra("mDate")!!

        setDetailStory()
        updateFavorite()
    }

    private fun setDetailStory() {
        lifecycleScope.launchWhenResumed {
            if (job.isActive) job.cancel()
            job = launch {
                val isFavorited = favoritedViewModel.isStoryExist(dataId)

                Glide.with(this@DetailStoryActivity)
                    .load(dataImage)
                    .placeholder(R.drawable.loading)
                    .into(binding.detailPhoto)
                tvName.text = dataName
                tvDescription.text = Html.fromHtml(
                    generateDesc(
                        dataName,
                        dataDesc
                    )
                )
                tvTime.text = "Posted on $dataTime"

                if (isFavorited) ivFav.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
        }
    }

    private fun updateFavorite() {
        binding.detailFavorite.setOnClickListener {
            favStory = Story(dataId, dataImage, dataTime, dataName, dataDesc)

            lifecycleScope.launchWhenResumed {
                val isFavorited = favoritedViewModel.isStoryExist(dataId)

                if (updateJob.isActive) updateJob.cancel()
                updateJob = launch {
                    if (!isFavorited) {
                        favoritedViewModel.insert(favStory)
                        ivFav.setImageResource(R.drawable.ic_baseline_favorite_24)
                        showToast("Adding $dataName's story to favorites")
                    } else {
                        favoritedViewModel.delete(favStory)
                        ivFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                        showToast("Removing $dataName's story from favorites")
                    }
                }
            }
        }
    }

    private fun generateDesc(name: String, desc: String): String {
        val builder = StringBuilder()
        builder.append("<b>$name</b>")
            .append(" ")
            .append(desc)

        return builder.toString()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}