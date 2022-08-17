package com.example.core.data.source.local.repository

import androidx.lifecycle.LiveData
import com.example.core.data.source.local.entity.FavoritedStory
import com.example.core.data.source.local.room.FavoritedDao
import java.util.concurrent.ExecutorService

abstract class AbstractFavoritedRepository {
    abstract val mFavoritedDao: FavoritedDao

    abstract val executorService: ExecutorService

    abstract fun getAllFavorites(): LiveData<List<FavoritedStory>>

    abstract fun insert(user: FavoritedStory)

    abstract fun delete(user: FavoritedStory)

    abstract fun isStoryExist(id: String): Boolean
}