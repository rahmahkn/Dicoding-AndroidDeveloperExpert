package com.example.core.data.source.local

import com.example.core.data.source.local.entity.FavoritedStory
import com.example.core.data.source.local.room.FavoritedDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSource(private val favoritedDao: FavoritedDao) {

    fun getAllFavorites() = favoritedDao.getAllFavorites()

    suspend fun insert(story: FavoritedStory) =
        withContext(Dispatchers.IO) {
            favoritedDao.insert(story)
        }

    suspend fun delete(story: FavoritedStory) =
        withContext(Dispatchers.IO) {
            favoritedDao.delete(story)
        }

    suspend fun isStoryExist(id: String): Boolean =
        withContext(Dispatchers.IO) {
            favoritedDao.isStoryExist(id)
        }
}