package com.example.myapplication.repository

import com.example.myapplication.model.domain.GetStoryResponse
import com.example.myapplication.model.domain.LoginResponse
import com.example.myapplication.model.enums.NetworkResult
import com.example.myapplication.network.ApiService
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