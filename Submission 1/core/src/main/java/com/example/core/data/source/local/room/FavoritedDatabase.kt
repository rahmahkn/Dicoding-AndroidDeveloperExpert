package com.example.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.source.local.entity.FavoritedStory

@Database(entities = [FavoritedStory::class], version = 1)
abstract class FavoritedDatabase : RoomDatabase() {
    abstract fun favoritedDao(): FavoritedDao
}