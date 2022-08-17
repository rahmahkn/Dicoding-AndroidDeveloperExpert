package com.example.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.core.data.source.local.entity.FavoritedStory

@Dao
interface FavoritedDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: FavoritedStory)

    @Delete
    fun delete(user: FavoritedStory)

    @Query("SELECT * FROM favoritedStory")
    fun getAllFavorites(): LiveData<List<FavoritedStory>>

    @Query("SELECT EXISTS(SELECT * FROM favoritedStory WHERE id = :id)")
    fun isStoryExist(id: String): Boolean
}