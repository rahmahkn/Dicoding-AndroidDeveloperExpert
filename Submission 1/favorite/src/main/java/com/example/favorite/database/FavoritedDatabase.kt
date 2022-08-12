package com.example.favorite.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.favorite.model.FavoritedStory

@Database(entities = [FavoritedStory::class], version = 1)
abstract class FavoritedDatabase : RoomDatabase() {
    abstract fun favoritedDao(): FavoritedDao

    companion object {
        @Volatile
        private var INSTANCE: FavoritedDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavoritedDatabase {
            if (INSTANCE == null) {
                synchronized(FavoritedDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavoritedDatabase::class.java, "favorited_database"
                    )
                        .allowMainThreadQueries().build()
                }
            }
            return INSTANCE as FavoritedDatabase
        }
    }
}