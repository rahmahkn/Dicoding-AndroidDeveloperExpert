package com.example.favorite.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.favorite.model.FavoritedStory
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoritedRepository(application: Application) {
    private val mFavoritedDao: FavoritedDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoritedDatabase.getDatabase(application)
        mFavoritedDao = db.favoritedDao()
    }

    fun getAllFavorites(): LiveData<List<FavoritedStory>> = mFavoritedDao.getAllFavorites()

    fun insert(user: FavoritedStory) {
        executorService.execute { mFavoritedDao.insert(user) }
    }

    fun delete(user: FavoritedStory) {
        executorService.execute { mFavoritedDao.delete(user) }
    }

    fun isStoryExist(id: String): Boolean = mFavoritedDao.isStoryExist(id)
}