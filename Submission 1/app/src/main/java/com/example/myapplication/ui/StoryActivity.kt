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
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.helper.SessionPreference
import com.example.core.helper.TokenPreference
import com.example.myapplication.BuildConfig
import com.example.myapplication.R
import com.example.myapplication.model.domain.GetStoryResponse
import com.example.myapplication.model.domain.LoginResponse
import com.example.myapplication.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoryActivity : AppCompatActivity() {
    private lateinit var rvStories: RecyclerView
    private lateinit var mTokenPreference: TokenPreference
    private lateinit var mSessionPreference: SessionPreference

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
                    "com.example.favorite.view.FavoriteStoryActivity"
                )
                startActivity(intent)
            }
        }
        return true
    }

    private fun getStories(token: String) {
        showLoading(true)

        val client = ApiConfig.getApiService().getStories("Bearer $token")
        client.enqueue(object : Callback<GetStoryResponse> {
            override fun onResponse(
                call: Call<GetStoryResponse>,
                response: Response<GetStoryResponse>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {

                        if (responseBody.listStory.isEmpty()) {
                            Log.d("GetStories", "empty")
                        } else {
                            rvStories.layoutManager = LinearLayoutManager(this@StoryActivity)

                            val listStoryFinal =
                                responseBody.listStory.sortedByDescending { it.createdAt }
                            val listStoriesAdapter = StoryAdapter(listStoryFinal)
                            rvStories.adapter = listStoriesAdapter

                        }
                    } else {
                        Log.d("GetStories", "null")
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GetStoryResponse>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun postLogin(email: String, password: String) {
        showLoading(true)
        val client = ApiConfig.getApiService().postLogin(email, password)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                showLoading(false)

                val responseBody = response.body()

                if (response.isSuccessful && responseBody != null) {
                    Log.e(TAG, "onSuccess: ${response.message()}")

                    mTokenPreference.setToken(responseBody.loginResult.token)
                    mSessionPreference.setSession()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                showLoading(false)
                Toast.makeText(this@StoryActivity, t.message, Toast.LENGTH_SHORT)
                    .show()
            }
        })
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