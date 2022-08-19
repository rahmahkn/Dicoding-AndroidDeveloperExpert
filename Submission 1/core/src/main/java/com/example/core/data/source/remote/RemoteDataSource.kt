package com.example.core.data.source.remote

import android.util.Log
import com.example.core.data.source.remote.network.ApiService
import com.example.myapplication.helper.getErrorMessageFromApi
import com.example.myapplication.model.domain.GetStoryResponse
import com.example.myapplication.model.domain.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class RemoteDataSource(private val apiService: ApiService) {

//    hapus kode berikut
//    companion object {
//        @Volatile
//        private var instance: RemoteDataSource? = null
//
//        fun getInstance(service: ApiService): RemoteDataSource =
//            instance ?: synchronized(this) {
//                instance ?: RemoteDataSource(service)
//            }
//    }

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

            Log.d("TEST LOGIN", response.body()!!.toString())

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

