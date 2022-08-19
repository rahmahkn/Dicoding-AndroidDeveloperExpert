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
    override suspend fun getStories(authHeader: String): Flow<NetworkResult<GetStoryResponse>> {
        return remoteDataSource.getStories(authHeader)
    }

    override suspend fun postLogin(
        email: String,
        password: String
    ): Flow<NetworkResult<LoginResponse>> {
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

    override suspend fun isStoryExist(id: String): Boolean {
        return localDataSource.isStoryExist(id)
    }
}

