package com.dicoding.tourismapp.core.data

import com.dicoding.tourismapp.core.domain.model.Story
import com.dicoding.tourismapp.core.domain.repository.IStoryRepository
import com.dicoding.tourismapp.core.utils.AppExecutors
import com.dicoding.tourismapp.core.utils.DataMapper
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.NetworkResult
import com.example.core.data.source.remote.RemoteDataSource
import com.example.myapplication.model.domain.GetStoryResponse
import com.example.myapplication.model.domain.LoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoryRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IStoryRepository {
    override fun getStories(authHeader: String): Flow<NetworkResult<GetStoryResponse>> {
        return remoteDataSource.getStories(authHeader)
    }

//    hapus kode berikut
//    companion object {
//        @Volatile
//        private var instance: TourismRepository? = null
//
//        fun getInstance(
//            remoteData: RemoteDataSource,
//            localData: LocalDataSource,
//            appExecutors: AppExecutors
//        ): TourismRepository =
//            instance ?: synchronized(this) {
//                instance ?: TourismRepository(remoteData, localData, appExecutors)
//            }
//    }


    //    override fun getAllTourism(): Flow<Resource<List<Tourism>>> =
//        object : NetworkBoundResource<List<Tourism>, List<TourismResponse>>() {
//            override fun loadFromDB(): Flow<List<Tourism>> {
//                return localDataSource.getAllTourism().map {
//                    DataMapper.mapEntitiesToDomain(it)
//                }
//            }
//
//            override fun shouldFetch(data: List<Tourism>?): Boolean =
////                data == null || data.isEmpty()
//                 true // ganti dengan true jika ingin selalu mengambil data dari internet
//
//            override fun createCall(): Flow<ApiResponse<List<TourismResponse>>> =
//                remoteDataSource.getAllTourism()
//
//            override fun saveCallResult(data: List<TourismResponse>) {
//                val tourismList = DataMapper.mapResponsesToEntities(data)
//                localDataSource.insertTourism(tourismList)
//            }
//        }.asFlow()
//
    override fun postLogin(email: String, password: String): Flow<NetworkResult<LoginResponse>> {
        return remoteDataSource.postLogin(email, password)
    }

    override fun getAllFavorites(): Flow<List<Story>> {
        return localDataSource.getAllFavorites().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun insert(user: Story) {
        val tourismEntity = DataMapper.mapDomainToEntity(user)
        appExecutors.diskIO().execute { localDataSource.insert(tourismEntity) }
    }

    override fun delete(user: Story) {
        val tourismEntity = DataMapper.mapDomainToEntity(user)
        appExecutors.diskIO().execute { localDataSource.delete(tourismEntity) }
    }

    override fun isStoryExist(id: String): Boolean {
        return localDataSource.isStoryExist(id)
    }
}

