package com.example.core.domain.usecase

import com.example.core.data.source.remote.NetworkResult
import com.example.core.data.source.remote.network.GetStoryResponse
import com.example.core.data.source.remote.network.LoginResponse
import com.example.core.domain.model.Story
import kotlinx.coroutines.flow.Flow

interface StoryUseCase {

    suspend fun getStories(authHeader: String): Flow<NetworkResult<GetStoryResponse>>

    suspend fun postLogin(email: String, password: String): Flow<NetworkResult<LoginResponse>>

    fun getAllFavorites(): Flow<List<Story>>

    suspend fun insert(user: Story)

    suspend fun delete(user: Story)

    suspend fun isStoryExist(id: String): Boolean

}