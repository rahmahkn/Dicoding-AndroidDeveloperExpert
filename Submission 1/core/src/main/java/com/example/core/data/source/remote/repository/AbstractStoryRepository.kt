package com.example.core.data.source.remote.repository

import com.example.core.data.source.remote.NetworkResult
import com.example.core.data.source.remote.network.ApiService
import com.example.myapplication.model.domain.GetStoryResponse
import com.example.myapplication.model.domain.LoginResponse
import kotlinx.coroutines.flow.Flow

abstract class AbstractStoryRepository {

    abstract val apiService: ApiService

    abstract suspend fun postLogin(
        email: String,
        password: String
    ): Flow<NetworkResult<LoginResponse>>

    abstract suspend fun getStories(
        authHeader: String
    ): Flow<NetworkResult<GetStoryResponse>>

}