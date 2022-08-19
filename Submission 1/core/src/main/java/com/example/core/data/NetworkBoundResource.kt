package com.dicoding.tourismapp.core.data

//abstract class NetworkBoundResource<ResultType, RequestType> {
//
//    private var result: Flow<NetworkResult<ResultType>> = flow {
//        emit(NetworkResult.Loading)
//        val dbSource = loadFromDB().first()
//        if (shouldFetch(dbSource)) {
//            emit(NetworkResult.Loading)
//            when (val apiResponse = createCall().first()) {
//                is ApiResponse.Success -> {
//                    saveCallResult(apiResponse.data)
//                    emitAll(loadFromDB().map { NetworkResult.Success(it) })
//                }
//                is ApiResponse.Empty -> {
//                    emitAll(loadFromDB().map { NetworkResult.Success(it) })
//                }
//                is ApiResponse.Error -> {
//                    onFetchFailed()
//                    emit(NetworkResult.Error(apiResponse.errorMessage))
//                }
//            }
//        } else {
//            emitAll(loadFromDB().map { NetworkResult.Success(it) })
//        }
//    }
//
//    protected open fun onFetchFailed() {}
//
//    protected abstract fun loadFromDB(): Flow<ResultType>
//
//    protected abstract fun shouldFetch(data: ResultType?): Boolean
//
//    protected abstract fun createCall(): Flow<NetworkResult<RequestType>>
//
//    protected abstract fun saveCallResult(data: RequestType)
//
//    fun asFlow(): Flow<NetworkResult<ResultType>> = result
//}