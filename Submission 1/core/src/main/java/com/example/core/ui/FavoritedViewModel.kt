package com.example.core.ui

import androidx.lifecycle.ViewModel
import com.example.core.domain.model.Story
import com.example.core.domain.usecase.StoryUseCase
import kotlinx.coroutines.flow.Flow

class FavoritedViewModel(private val storyUseCase: StoryUseCase) : ViewModel() {

    fun getAllFavorites(): Flow<List<Story>> = storyUseCase.getAllFavorites()

    suspend fun insert(user: Story) = storyUseCase.insert(user)

    suspend fun delete(user: Story) = storyUseCase.delete(user)

    suspend fun isStoryExist(id: String): Boolean = storyUseCase.isStoryExist(id)
}