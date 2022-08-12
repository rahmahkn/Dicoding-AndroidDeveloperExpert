package com.example.favorite.repository

import androidx.lifecycle.LiveData
import com.example.favorite.database.FavoritedDao
import com.example.favorite.model.data.FavoritedStory
import java.util.concurrent.ExecutorService

abstract class AbstractFavoritedRepository {
    abstract val mFavoritedDao: FavoritedDao

    abstract val executorService: ExecutorService

    abstract fun getAllFavorites(): LiveData<List<FavoritedStory>>

    abstract fun insert(user: FavoritedStory)

    abstract fun delete(user: FavoritedStory)

    abstract fun isStoryExist(id: String): Boolean
}