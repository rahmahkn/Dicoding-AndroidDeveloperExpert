package com.example.myapplication.ui

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.source.preference.SessionPreference
import com.example.core.data.source.preference.TokenPreference
import com.example.core.data.source.remote.NetworkResult
import com.example.core.ui.StoryAdapter
import com.example.myapplication.BuildConfig
import com.example.myapplication.R
import com.example.myapplication.viewmodel.StoryViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class StoryActivity : AppCompatActivity() {
    private lateinit var rvStories: RecyclerView
    private lateinit var mTokenPreference: TokenPreference
    private lateinit var mSessionPreference: SessionPreference

    private val viewModel: StoryViewModel by viewModel()
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

    override fun onBackPressed() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q &&
            isTaskRoot &&
            (supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.backStackEntryCount
                ?: 0) == 0 &&
            supportFragmentManager.backStackEntryCount == 0
        ) {
            finishAfterTransition()
        } else {
            super.onBackPressed()
        }
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

                                val listStory =
                                    responseBody.listStory.sortedByDescending { it.createdAt }
                                val listStoryAdapter =
                                    StoryAdapter(listStory)
                                rvStories.adapter = listStoryAdapter

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

                            Toast.makeText(applicationContext, "Loading..", Toast.LENGTH_SHORT)
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