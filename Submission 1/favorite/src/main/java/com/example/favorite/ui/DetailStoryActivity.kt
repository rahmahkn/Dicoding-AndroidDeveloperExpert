package com.example.favorite.ui

//import com.example.favorite.viewmodel.ViewModelFactory
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.dicoding.tourismapp.core.utils.DataMapper
import com.example.core.data.source.local.entity.FavoritedStory
import com.example.core.ui.FavoritedViewModel
import com.example.favorite.R
import com.example.favorite.databinding.ActivityDetailStoryBinding
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailStoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailStoryBinding
    private lateinit var ivFoto: ImageView
    private lateinit var tvNama: TextView
    private lateinit var tvDeskripsi: TextView
    private lateinit var tvTime: TextView

    //    private lateinit var favoritedViewModel: FavoritedViewModel
    private val favoritedViewModel: FavoritedViewModel by viewModel()
    private var job: Job = Job()
    private var isFavorited: Boolean = false

    private lateinit var favStory: FavoritedStory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        favoritedViewModel = obtainViewModel(this@DetailStoryActivity)

        ivFoto = binding.detailFoto
        tvNama = binding.detailNama
        tvDeskripsi = binding.detailDeskripsi
        tvTime = binding.detailWaktu

        val dataId = intent.getStringExtra("mId")!!
        val dataImage = intent.getStringExtra("mPhotoUrl")!!
        val dataName = intent.getStringExtra("mName")!!
        val dataDesc = intent.getStringExtra("mDescription")!!
        val dataTime = intent.getStringExtra("mDate")!!

        isFavorited(dataId)

        Glide.with(this@DetailStoryActivity)
            .load(dataImage)
            .placeholder(R.drawable.loading)
            .into(binding.detailFoto)
        tvNama.text = dataName
        tvDeskripsi.text = Html.fromHtml(
            generateDesc(
                dataName,
                dataDesc
            )
        )
        tvTime.text = "Posted on $dataTime"

//        var isFavorited = favoritedViewModel.isStoryExist(dataId)
        val fabFav = binding.detailFavorite

        if (isFavorited) fabFav.setImageResource(R.drawable.ic_baseline_favorite_24)

        binding.detailFavorite.setOnClickListener {
            favStory = FavoritedStory(dataId, dataImage, dataTime, dataName, dataDesc)

            isFavorited = if (!isFavorited) {
                favoritedViewModel.insert(DataMapper.mapEntityToDomain(favStory))
                fabFav.setImageResource(R.drawable.ic_baseline_favorite_24)
                showToast("Adding $dataName's story to favorites")
                true
            } else {
                favoritedViewModel.delete(DataMapper.mapEntityToDomain(favStory))
                fabFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                showToast("Removing $dataName's story from favorites")
                false
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

//    private fun obtainViewModel(activity: AppCompatActivity): FavoritedViewModel {
//        val factory = ViewModelFactory.getInstance(activity.application)
//        return ViewModelProvider(activity, factory)[FavoritedViewModel::class.java]
//    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun isFavorited(id: String) {
        lifecycleScope.launchWhenResumed {
            if (job.isActive) job.cancel()
            job = launch {
                favoritedViewModel.isStoryExist(id).collect {
                    isFavorited = it
                }
            }
        }
    }
}