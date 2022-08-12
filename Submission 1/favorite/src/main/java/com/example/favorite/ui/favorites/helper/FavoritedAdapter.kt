package com.example.favorite.ui.favorites.helper

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.favorite.R
import com.example.favorite.databinding.ItemFavoriteStoryBinding
import com.example.favorite.model.FavoritedStory
import java.text.SimpleDateFormat

class FavoritedAdapter(private val listFavorited: List<FavoritedStory>) :
    RecyclerView.Adapter<FavoritedAdapter.FavoritedViewHolder>() {
    fun setListNotes(listFavoritedFinal: ArrayList<FavoritedStory>) {
        val diffCallback = FavoritedDiffCallback(listFavorited, listFavoritedFinal)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        listFavoritedFinal.clear()
        listFavoritedFinal.addAll(listFavorited)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritedViewHolder {
        val binding =
            ItemFavoriteStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FavoritedViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: FavoritedViewHolder, position: Int) {
        viewHolder.bind(listFavorited[position])
    }

    override fun getItemCount() = listFavorited.size

    inner class FavoritedViewHolder(private val binding: ItemFavoriteStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(story: FavoritedStory) {

            Glide.with(this@FavoritedViewHolder.itemView.context)
                .load(story.photoUrl)
//                .placeholder(R.drawable.loading)
                .into(binding.itemImage)

            binding.itemName.text = story.name
            binding.itemDescription.text = story.description

            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
            val formattedDate = formatter.format(parser.parse(story.createdAt))
            binding.itemTime.text = formattedDate

            itemView.setOnClickListener {
//                val intent = Intent(it.context, DetailStoryActivity::class.java)
//                intent.putExtra(DetailStoryActivity.EXTRA_IMAGE, story.photoUrl)
//                intent.putExtra(DetailStoryActivity.EXTRA_NAME, story.name)
//                intent.putExtra(DetailStoryActivity.EXTRA_DESC, story.description)
//                intent.putExtra(DetailStoryActivity.EXTRA_TIME, formattedDate)
//
//                val optionsCompat: ActivityOptionsCompat =
//                    ActivityOptionsCompat.makeSceneTransitionAnimation(
//                        it.context as Activity,
//                        androidx.core.util.Pair(binding.itemImage, "foto"),
//                        androidx.core.util.Pair(binding.itemName, "nama"),
//                        androidx.core.util.Pair(binding.itemDescription, "deskripsi"),
//                        androidx.core.util.Pair(binding.itemTime, "waktu")
//                    )
//                it.context.startActivity(intent, optionsCompat.toBundle())
//            }
            }
        }
    }
}