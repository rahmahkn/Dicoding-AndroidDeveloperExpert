package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.repository.AbstractStoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor(
    private val storyRepository: AbstractStoryRepository
) : ViewModel() {

    suspend fun postLogin(email: String, password: String) =
        storyRepository.postLogin(email, password)

    suspend fun getStories(authHeader: String) =
        storyRepository.getStories(authHeader)
}