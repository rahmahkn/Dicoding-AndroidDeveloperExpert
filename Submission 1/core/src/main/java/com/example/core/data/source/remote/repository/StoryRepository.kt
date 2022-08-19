package com.example.core.data.source.remote.repository

//@Module
//@InstallIn(ViewModelComponent::class)
//class StoryRepository @Inject constructor(
//    override val apiService: ApiService
//
//) : AbstractStoryRepository() {
//    override fun postLogin(
//        email: String,
//        password: String
//    ) = flow {
//
//        emit(NetworkResult.Loading)
//
//        try {
//            val response: Response<LoginResponse> =
//                apiService.postLogin(
//                    email, password
//                )
//
//            when (response.code()) {
//                200 -> emit(NetworkResult.Success(response.body()!!))
//
//                else -> emit(NetworkResult.Error(getErrorMessageFromApi(response, "message")))
//            }
//
//        } catch (e: Exception) {
//
//            emit(NetworkResult.Error(e.message.toString()))
//        }
//    }.flowOn(Dispatchers.IO)
//
//    override suspend fun getStories(authHeader: String) = flow {
//
//        emit(NetworkResult.Loading)
//
//        try {
//            val response: Response<GetStoryResponse> =
//                apiService.getStories(
//                    authHeader
//                )
//
//            when (response.code()) {
//                200 -> emit(NetworkResult.Success(response.body()!!))
//
//                else -> emit(NetworkResult.Error(getErrorMessageFromApi(response, "message")))
//            }
//
//        } catch (e: Exception) {
//
//            emit(NetworkResult.Error(e.message.toString()))
//        }
//    }.flowOn(Dispatchers.IO)
//
//}