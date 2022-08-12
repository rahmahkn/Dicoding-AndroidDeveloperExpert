package com.example.favorite.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.favorite.model.FavoritedStory

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