package com.dicoding.tourismapp.core.domain.repository

import com.dicoding.tourismapp.core.domain.model.Story
import com.example.core.data.source.remote.NetworkResult
import com.example.myapplication.model.domain.GetStoryResponse
import com.example.myapplication.model.domain.LoginResponse
import kotlinx.coroutines.flow.Flow

interface IStoryRepository {

    fun getStories(authHeader: String): Flow<NetworkResult<GetStoryResponse>>

    fun postLogin(email: String, password: String): Flow<NetworkResult<LoginResponse>>

    fun getAllFavorites(): Flow<List<Story>>

    fun insert(user: Story)

    fun delete(user: Story)

    fun isStoryExist(id: String): Boolean

}