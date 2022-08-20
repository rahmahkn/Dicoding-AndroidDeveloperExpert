package com.example.core.data

import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.NetworkResult
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.GetStoryResponse
import com.example.core.data.source.remote.network.LoginResponse
import com.example.core.domain.model.Story
import com.example.core.domain.repository.IStoryRepository
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoryRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
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

    override suspend fun insert(user: Story) {
        val storyEntity = DataMapper.mapDomainToEntity(user)
        localDataSource.insert(storyEntity)
    }

    override suspend fun delete(user: Story) {
        val storyEntity = DataMapper.mapDomainToEntity(user)
        localDataSource.delete(storyEntity)
    }

    override suspend fun isStoryExist(id: String): Boolean {
        return localDataSource.isStoryExist(id)
    }
}

