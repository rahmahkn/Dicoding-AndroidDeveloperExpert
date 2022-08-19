package com.example.core.data.source.remote

import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.network.GetStoryResponse
import com.example.core.data.source.remote.network.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun postLogin(
        email: String,
        password: String
    ) = flow {

        emit(NetworkResult.Loading)

        try {
            val response: Response<LoginResponse> =
                apiService.postLogin(
                    email, password
                )

            when (response.code()) {
                200 -> emit(NetworkResult.Success(response.body()!!))

                else -> emit(NetworkResult.Error(getErrorMessageFromApi(response, "message")))
            }

        } catch (e: Exception) {

            emit(NetworkResult.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getStories(authHeader: String) = flow {

        emit(NetworkResult.Loading)

        try {
            val response: Response<GetStoryResponse> =
                apiService.getStories(
                    authHeader
                )

            when (response.code()) {
                200 -> emit(NetworkResult.Success(response.body()!!))

                else -> emit(NetworkResult.Error(getErrorMessageFromApi(response, "message")))
            }

        } catch (e: Exception) {

            emit(NetworkResult.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}

