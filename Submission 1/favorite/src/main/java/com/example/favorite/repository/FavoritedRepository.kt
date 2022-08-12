package com.example.favorite.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.favorite.database.FavoritedDao
import com.example.favorite.database.FavoritedDatabase
import com.example.favorite.model.data.FavoritedStory
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

class FavoritedRepository @Inject constructor(application: Application) :
    AbstractFavoritedRepository() {
    override val mFavoritedDao: FavoritedDao
    override val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoritedDatabase.getDatabase(application)
        mFavoritedDao = db.favoritedDao()
    }

    override fun getAllFavorites(): LiveData<List<FavoritedStory>> = mFavoritedDao.getAllFavorites()

    override fun insert(user: FavoritedStory) {
        executorService.execute { mFavoritedDao.insert(user) }
    }

    override fun delete(user: FavoritedStory) {
        executorService.execute { mFavoritedDao.delete(user) }
    }

    override fun isStoryExist(id: String): Boolean = mFavoritedDao.isStoryExist(id)
}