package com.example.core.data.source.local

import com.example.core.data.source.local.entity.FavoritedStory
import com.example.core.data.source.local.room.FavoritedDao

class LocalDataSource(private val favoritedDao: FavoritedDao) {

    fun getAllFavorites() = favoritedDao.getAllFavorites()

    suspend fun insert(story: FavoritedStory) = favoritedDao.insert(story)

    suspend fun delete(story: FavoritedStory) = favoritedDao.delete(story)

    suspend fun isStoryExist(id: String): Boolean = favoritedDao.isStoryExist(id)

}