package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.core.domain.usecase.StoryUseCase

class StoryViewModel(private val storyUseCase: StoryUseCase) : ViewModel() {
    suspend fun postLogin(email: String, password: String) =
        storyUseCase.postLogin(email, password)

    suspend fun getStories(authHeader: String) =
        storyUseCase.getStories(authHeader)
}