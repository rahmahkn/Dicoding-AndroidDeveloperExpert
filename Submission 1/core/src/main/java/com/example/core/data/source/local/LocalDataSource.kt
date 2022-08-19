package com.example.core.data.source.local

import com.example.core.data.source.local.entity.FavoritedStory
import com.example.core.data.source.local.room.FavoritedDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSource(private val favoritedDao: FavoritedDao) {

    fun getAllFavorites() = favoritedDao.getAllFavorites()

    fun insert(user: FavoritedStory) = favoritedDao.insert(user)

    fun delete(user: FavoritedStory) = favoritedDao.delete(user)

    suspend fun isStoryExist(id: String): Boolean =
        withContext(Dispatchers.IO) {
            favoritedDao.isStoryExist(id)
        }
}