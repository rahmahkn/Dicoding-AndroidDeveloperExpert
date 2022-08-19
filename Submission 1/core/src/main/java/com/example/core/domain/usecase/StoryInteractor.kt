package com.dicoding.tourismapp.core.domain.usecase

import com.dicoding.tourismapp.core.domain.model.Story
import com.dicoding.tourismapp.core.domain.repository.IStoryRepository
import com.example.core.data.source.remote.NetworkResult
import com.example.myapplication.model.domain.GetStoryResponse
import kotlinx.coroutines.flow.Flow

class StoryInteractor(private val storyRepository: IStoryRepository) : StoryUseCase {

    override suspend fun getStories(authHeader: String): Flow<NetworkResult<GetStoryResponse>> =
        storyRepository.getStories(authHeader)

    override suspend fun postLogin(email: String, password: String) =
        storyRepository.postLogin(email, password)

    override fun getAllFavorites() = storyRepository.getAllFavorites()

    override fun insert(user: Story) = storyRepository.insert(user)

    override fun delete(user: Story) = storyRepository.delete(user)

    override fun isStoryExist(id: String) = storyRepository.isStoryExist(id)

}