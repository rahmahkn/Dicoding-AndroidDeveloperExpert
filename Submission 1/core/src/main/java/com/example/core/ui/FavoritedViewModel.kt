package com.example.core.ui

import androidx.lifecycle.ViewModel
import com.dicoding.tourismapp.core.domain.model.Story
import com.dicoding.tourismapp.core.domain.usecase.StoryUseCase
import kotlinx.coroutines.flow.Flow

class FavoritedViewModel(val storyUseCase: StoryUseCase) : ViewModel() {

    fun getAllFavorites(): Flow<List<Story>> = storyUseCase.getAllFavorites()

    fun insert(user: Story) = storyUseCase.insert(user)

    fun delete(user: Story) = storyUseCase.delete(user)

    suspend fun isStoryExist(id: String): Boolean = storyUseCase.isStoryExist(id)
}