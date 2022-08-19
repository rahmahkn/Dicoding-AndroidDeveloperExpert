package com.example.favorite.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.ui.FavoritedViewModel
import com.example.favorite.R
import com.example.favorite.adapter.FavoritedAdapter
import com.example.favorite.databinding.ActivityFavoriteStoryBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritedStoryActivity : AppCompatActivity() {
    private lateinit var rvUsers: RecyclerView
    private var _activityFavoriteBinding: ActivityFavoriteStoryBinding? = null
    private val binding get() = _activityFavoriteBinding
    private lateinit var adapter: FavoritedAdapter
    private val mainViewModel: FavoritedViewModel by viewModel()
    private var storyJob: Job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityFavoriteBinding = ActivityFavoriteStoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        rvUsers = findViewById(R.id.rv_stories)

        getFavorites()
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityFavoriteBinding = null
    }

    private fun getFavorites() {
        val layoutManager = LinearLayoutManager(this)

        lifecycleScope.launchWhenResumed {
            if (storyJob.isActive) storyJob.cancel()

            storyJob = launch {
                mainViewModel.getAllFavorites().collect { userList ->

                    adapter = FavoritedAdapter(userList)

                    binding?.rvStories?.layoutManager = layoutManager
                    binding?.rvStories?.setHasFixedSize(true)
                    binding?.rvStories?.adapter = adapter
                }
            }
        }
    }
}