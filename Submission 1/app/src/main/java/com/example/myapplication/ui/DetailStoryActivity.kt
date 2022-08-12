package com.example.myapplication.ui

import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
//import com.example.favorite.model.FavoritedStory
//import com.example.favorite.ui.favorites.helper.FavoritedAddDeleteViewModel
//import com.example.favorite.ui.favorites.helper.ViewModelFactory
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityDetailStoryBinding

class DetailStoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailStoryBinding
    private lateinit var ivFoto: ImageView
    private lateinit var tvNama: TextView
    private lateinit var tvDeskripsi: TextView
    private lateinit var tvTime: TextView
//    private lateinit var favoritedAddDeleteViewModel: FavoritedAddDeleteViewModel
//    private lateinit var favStory: FavoritedStory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        favoritedAddDeleteViewModel = obtainViewModel(this@DetailStoryActivity)

        ivFoto = binding.detailFoto
        tvNama = binding.detailNama
        tvDeskripsi = binding.detailDeskripsi
        tvTime = binding.detailWaktu

        val dataId = intent.getStringExtra(EXTRA_ID)!!
        val dataImage = intent.getStringExtra(EXTRA_IMAGE)!!
        val dataName = intent.getStringExtra(EXTRA_NAME)!!
        val dataDesc = intent.getStringExtra(EXTRA_DESC)!!
        val dataTime = intent.getStringExtra(EXTRA_TIME)!!

        Glide.with(this@DetailStoryActivity)
            .load(intent.getStringExtra(EXTRA_IMAGE))
            .placeholder(R.drawable.loading)
            .into(binding.detailFoto)
        tvNama.text = intent.getStringExtra(EXTRA_NAME)
        tvDeskripsi.text = Html.fromHtml(
            generateDesc(
                dataName,
                dataDesc
            )
        )
        tvTime.text = "Posted on " + dataTime

//        var isFavorited = favoritedAddDeleteViewModel.isStoryExist(dataId)
//        val fabFav = findViewById<ImageView>(R.id.iv_favorite)
//
//        if (isFavorited) fabFav.setImageResource(R.drawable.ic_baseline_favorite_24)
//
//        binding.ivFavorite.setOnClickListener {
//            favStory = FavoritedStory(dataId, dataImage, dataTime, dataName, dataDesc)
//
//            isFavorited = if (!isFavorited) {
//                favoritedAddDeleteViewModel.insert(favStory)
//                fabFav.setImageResource(R.drawable.ic_baseline_favorite_24)
//                showToast("Adding $dataId to favorites")
//                true
//            } else {
//                favoritedAddDeleteViewModel.delete(favStory)
//                fabFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
//                showToast("Removing $dataId from favorites")
//                false
//            }
//        }
    }

    private fun generateDesc(name: String, desc: String): String {
        val builder = StringBuilder()
        builder.append("<b>$name</b>")
            .append(" ")
            .append(desc)

        return builder.toString()
    }

//    private fun obtainViewModel(activity: AppCompatActivity): FavoritedAddDeleteViewModel {
//        val factory = ViewModelFactory.getInstance(activity.application)
//        return ViewModelProvider(activity, factory)[FavoritedAddDeleteViewModel::class.java]
//    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "DetailStoryActivity"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_IMAGE = "extra_image"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_DESC = "extra_desc"
        const val EXTRA_TIME = "extra_time"
    }
}