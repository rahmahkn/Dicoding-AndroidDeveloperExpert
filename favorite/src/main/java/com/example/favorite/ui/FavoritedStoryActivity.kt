package com.example.favorite.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.ui.StoryAdapter
import com.example.favorite.R
import com.example.favorite.databinding.ActivityFavoriteStoryBinding
import com.example.favorite.di.favoritedViewModelModule
import com.example.favorite.viewmodel.FavoritedViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoritedStoryActivity : AppCompatActivity() {
    private lateinit var rvUsers: RecyclerView
    private var _activityFavoriteBinding: ActivityFavoriteStoryBinding? = null
    private val binding get() = _activityFavoriteBinding
    private lateinit var adapter: StoryAdapter
    private val mainViewModel: FavoritedViewModel by viewModel()
    private var storyJob: Job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityFavoriteBinding = ActivityFavoriteStoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        loadKoinModules(favoritedViewModelModule)

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
                mainViewModel.getAllFavorites().collect { listStory ->

                    adapter = StoryAdapter(listStory)

                    binding?.rvStories?.layoutManager = layoutManager
                    binding?.rvStories?.setHasFixedSize(true)
                    binding?.rvStories?.adapter = adapter
                }
            }
        }
    }
}