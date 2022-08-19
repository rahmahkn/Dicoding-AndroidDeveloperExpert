package com.example.core.data.source.local

import com.example.core.data.source.local.entity.FavoritedStory
import com.example.core.data.source.local.room.FavoritedDao

class LocalDataSource(private val favoritedDao: FavoritedDao) {

//    hapus kode berikut
//    companion object {
//        private var instance: LocalDataSource? = null
//
//        fun getInstance(tourismDao: TourismDao): LocalDataSource =
//            instance ?: synchronized(this) {
//                instance ?: LocalDataSource(tourismDao)
//            }
//    }

    fun getAllFavorites() = favoritedDao.getAllFavorites()

    fun insert(user: FavoritedStory) = favoritedDao.insert(user)

    fun delete(user: FavoritedStory) = favoritedDao.delete(user)

    fun isStoryExist(id: String) = favoritedDao.isStoryExist(id)
}