package com.example.myapplication.ui

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.SessionPreference
import com.example.core.data.TokenPreference
import com.example.core.data.source.remote.NetworkResult
import com.example.myapplication.BuildConfig
import com.example.myapplication.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StoryActivity : AppCompatActivity() {
    private lateinit var rvStories: RecyclerView
    private lateinit var mTokenPreference: TokenPreference
    private lateinit var mSessionPreference: SessionPreference

    private val viewModel by viewModels<StoryViewModel>()
    private var loginJob: Job = Job()
    private var storyJob: Job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        rvStories = findViewById(R.id.rv_stories)

        mSessionPreference = SessionPreference(this)

        mTokenPreference = TokenPreference(this)
        val token = mTokenPreference.getToken()

        postLogin("rahmahtesting@gmail.com", "rahmahtesting")
        getStories(token)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favorite -> {

                val intent = Intent()
                intent.setClassName(
                    BuildConfig.APPLICATION_ID,
                    "com.example.favorite.ui.FavoritedStoryActivity"
                )
                startActivity(intent)
            }
        }
        return true
    }

    private fun getStories(token: String) {
        showLoading(true)

        lifecycleScope.launchWhenResumed {
            if (storyJob.isActive) storyJob.cancel()

            storyJob = launch {

                viewModel.getStories("Bearer $token").collect { result ->
                    when (result) {
                        is NetworkResult.Loading -> showLoading(true)

                        is NetworkResult.Success -> {
                            showLoading(false)

                            val responseBody = result.data
                            if (responseBody.listStory.isEmpty()) {
                                Log.d("GetStories", "empty")
                            } else {
                                rvStories.layoutManager = LinearLayoutManager(this@StoryActivity)

                                val listStoryFinal =
                                    responseBody.listStory.sortedByDescending { it.createdAt }
                                val listStoriesAdapter = StoryAdapter(listStoryFinal)
                                rvStories.adapter = listStoriesAdapter

                            }
                        }

                        is NetworkResult.Error -> {
                            showLoading(false)
                            Log.e(TAG, "onFailure: ${result.message}")
                        }
                    }
                }
            }
        }
    }

    private fun postLogin(email: String, password: String) {

        lifecycleScope.launchWhenResumed {
            if (loginJob.isActive) loginJob.cancel()
            loginJob = launch {

                viewModel.postLogin(email, password).collect { result ->
                    when (result) {
                        is NetworkResult.Loading -> {
                            showLoading(true)

                            Toast.makeText(this@StoryActivity, "Loading..", Toast.LENGTH_SHORT)
                                .show()
                        }


                        is NetworkResult.Success -> {
                            showLoading(false)

                            val responseBody = result.data
                            Log.d(TAG, "onSuccess: ${responseBody.message}")

                            mTokenPreference.setToken(responseBody.loginResult.token)
                            mSessionPreference.setSession()

                        }

                        is NetworkResult.Error -> {
                            showLoading(false)

                            Toast.makeText(
                                this@StoryActivity,
                                "Error in authentication",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        val pgBar: ProgressBar = findViewById(R.id.progress_bar)
        if (isLoading) {
            pgBar.visibility = View.VISIBLE
        } else {
            pgBar.visibility = View.GONE
        }
    }
}