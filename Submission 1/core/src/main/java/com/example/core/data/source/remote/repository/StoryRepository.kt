package com.example.core.data.source.remote.repository

import com.example.core.data.source.remote.NetworkResult
import com.example.core.data.source.remote.network.ApiService
import com.example.myapplication.helper.getErrorMessageFromApi
import com.example.myapplication.model.domain.GetStoryResponse
import com.example.myapplication.model.domain.LoginResponse
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class StoryRepository @Inject constructor(
    override val apiService: ApiService

) : AbstractStoryRepository() {
    override suspend fun postLogin(
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

    override suspend fun getStories(authHeader: String) = flow {

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