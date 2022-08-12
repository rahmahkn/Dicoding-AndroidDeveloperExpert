package com.example.myapplication.ui

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.domain.ListStoryItem
import java.text.SimpleDateFormat

class StoryAdapter(private val listStories: List<ListStoryItem>) :
    RecyclerView.Adapter<StoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_story, viewGroup, false)
        )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Glide.with(viewHolder.itemView.context)
            .load(listStories[position].photoUrl)
            .placeholder(R.drawable.loading)
            .into(viewHolder.ivImage)

        viewHolder.tvName.text = listStories[position].name
        viewHolder.tvDescription.text = listStories[position].description

        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
        val formattedDate = formatter.format(parser.parse(listStories[position].createdAt))
        viewHolder.tvTime.text = formattedDate

        viewHolder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailStoryActivity::class.java)
            intent.putExtra(DetailStoryActivity.EXTRA_ID, listStories[position].id)
            intent.putExtra(DetailStoryActivity.EXTRA_IMAGE, listStories[position].photoUrl)
            intent.putExtra(DetailStoryActivity.EXTRA_NAME, listStories[position].name)
            intent.putExtra(DetailStoryActivity.EXTRA_DESC, listStories[position].description)
            intent.putExtra(DetailStoryActivity.EXTRA_TIME, formattedDate)

            val optionsCompat: ActivityOptionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                    it.context as Activity,
                    androidx.core.util.Pair(viewHolder.ivImage, "foto"),
                    androidx.core.util.Pair(viewHolder.tvName, "nama"),
                    androidx.core.util.Pair(viewHolder.tvDescription, "deskripsi"),
                    androidx.core.util.Pair(viewHolder.tvTime, "waktu")
                )
            it.context.startActivity(intent, optionsCompat.toBundle())
        }
    }

    override fun getItemCount() = listStories.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivImage: ImageView = view.findViewById(R.id.item_image)
        val tvName: TextView = view.findViewById(R.id.item_name)
        val tvDescription: TextView = view.findViewById(R.id.item_description)
        val tvTime: TextView = view.findViewById(R.id.item_time)
    }
}