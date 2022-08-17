package com.example.favorite.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.data.source.local.entity.FavoritedStory
import com.example.core.data.source.local.repository.FavoritedDiffCallback
import com.example.favorite.R
import com.example.favorite.databinding.ItemFavoriteStoryBinding
import com.example.favorite.ui.DetailStoryActivity

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
                .placeholder(R.drawable.loading)
                .into(binding.itemImage)

            binding.itemName.text = story.name
            binding.itemDescription.text = story.description
            binding.itemTime.text = story.createdAt

            itemView.setOnClickListener {
                val intent = Intent(it.context, DetailStoryActivity::class.java)
                intent.putExtra("mId", story.id)
                intent.putExtra("mPhotoUrl", story.photoUrl)
                intent.putExtra("mName", story.name)
                intent.putExtra("mDescription", story.description)
                intent.putExtra("mDate", story.createdAt)

                val optionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        it.context as Activity,
                        androidx.core.util.Pair(binding.itemImage, "foto"),
                        androidx.core.util.Pair(binding.itemName, "nama"),
                        androidx.core.util.Pair(binding.itemDescription, "deskripsi"),
                        androidx.core.util.Pair(binding.itemTime, "waktu")
                    )
                it.context.startActivity(intent, optionsCompat.toBundle())
            }
        }
    }
}