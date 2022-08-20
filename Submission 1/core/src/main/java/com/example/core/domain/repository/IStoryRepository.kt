package com.example.core.domain.repository

import com.example.core.data.source.remote.NetworkResult
import com.example.core.domain.model.GetStoryModel
import com.example.core.domain.model.LoginModel
import com.example.core.domain.model.Story
import kotlinx.coroutines.flow.Flow

interface IStoryRepository {

    suspend fun getStories(authHeader: String): Flow<NetworkResult<GetStoryModel>>

    suspend fun postLogin(email: String, password: String): Flow<NetworkResult<LoginModel>>

    fun getAllFavorites(): Flow<List<Story>>

    suspend fun insert(user: Story)

    suspend fun delete(user: Story)

    suspend fun isStoryExist(id: String): Boolean

}