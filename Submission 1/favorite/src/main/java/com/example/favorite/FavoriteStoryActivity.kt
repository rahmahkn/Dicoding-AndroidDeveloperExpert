package com.example.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.favorite.databinding.ActivityFavoriteStoryBinding
import com.example.favorite.model.FavoritedStory
import com.example.favorite.ui.favorites.helper.FavoritedAdapter
import com.example.favorite.ui.favorites.helper.MainViewModel
import com.example.favorite.ui.favorites.helper.ViewModelFactory

class FavoriteStoryActivity : AppCompatActivity() {
    private lateinit var rvUsers: RecyclerView
    private var _activityFavoriteBinding: ActivityFavoriteStoryBinding? = null
    private val binding get() = _activityFavoriteBinding
    private lateinit var adapter: FavoritedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityFavoriteBinding = ActivityFavoriteStoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        rvUsers = findViewById(R.id.rv_stories)

        // setting up view model
        val mainViewModel = obtainViewModel(this@FavoriteStoryActivity)
        mainViewModel.getAllFavorites().observe(this) { userList ->
            if (userList != null) {
                // setting up adapter
                val listFavorited = ArrayList<FavoritedStory>()
                listFavorited.clear()
                listFavorited.addAll(userList)
                adapter = FavoritedAdapter(userList)
                adapter.setListNotes(listFavorited)

                val layoutManager = LinearLayoutManager(this)
                binding?.rvStories?.layoutManager = layoutManager
                binding?.rvStories?.setHasFixedSize(true)
                binding?.rvStories?.adapter = adapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityFavoriteBinding = null
    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MainViewModel::class.java]
    }
}