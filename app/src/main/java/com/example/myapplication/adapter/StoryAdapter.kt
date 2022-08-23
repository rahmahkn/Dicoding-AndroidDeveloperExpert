package com.example.myapplication.adapter

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
import com.example.core.domain.model.Story
import com.example.myapplication.BuildConfig
import com.example.myapplication.R
import java.text.SimpleDateFormat
import java.util.*

class StoryAdapter(private val listStories: List<Story>) :
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

        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        val formattedDate = formatter.format(parser.parse(listStories[position].createdAt)!!)
        viewHolder.tvTime.text = formattedDate

        viewHolder.itemView.setOnClickListener {
            val intent = Intent()
            intent.setClassName(
                BuildConfig.APPLICATION_ID,
                "com.example.favorite.ui.DetailStoryActivity"
            )
            intent.putExtra("mId", listStories[position].id)
            intent.putExtra("mPhotoUrl", listStories[position].photoUrl)
            intent.putExtra("mName", listStories[position].name)
            intent.putExtra("mDescription", listStories[position].description)
            intent.putExtra("mDate", formattedDate)

            val optionsCompat: ActivityOptionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                    it.context as Activity,
                    androidx.core.util.Pair(viewHolder.ivImage, "photo"),
                    androidx.core.util.Pair(viewHolder.tvName, "name"),
                    androidx.core.util.Pair(viewHolder.tvDescription, "description"),
                    androidx.core.util.Pair(viewHolder.tvTime, "time")
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