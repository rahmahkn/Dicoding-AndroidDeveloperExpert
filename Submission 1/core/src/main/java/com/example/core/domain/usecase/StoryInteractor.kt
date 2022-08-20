package com.example.core.domain.usecase

import com.example.core.data.source.remote.NetworkResult
import com.example.core.domain.model.GetStoryModel
import com.example.core.domain.model.LoginModel
import com.example.core.domain.model.Story
import com.example.core.domain.repository.IStoryRepository
import kotlinx.coroutines.flow.Flow

class StoryInteractor(private val storyRepository: IStoryRepository) : StoryUseCase {

    override suspend fun getStories(authHeader: String): Flow<NetworkResult<GetStoryModel>> =
        storyRepository.getStories(authHeader)

    override suspend fun postLogin(
        email: String,
        password: String
    ): Flow<NetworkResult<LoginModel>> =
        storyRepository.postLogin(email, password)

    override fun getAllFavorites() = storyRepository.getAllFavorites()

    override suspend fun insert(user: Story) = storyRepository.insert(user)

    override suspend fun delete(user: Story) = storyRepository.delete(user)

    override suspend fun isStoryExist(id: String) = storyRepository.isStoryExist(id)

}