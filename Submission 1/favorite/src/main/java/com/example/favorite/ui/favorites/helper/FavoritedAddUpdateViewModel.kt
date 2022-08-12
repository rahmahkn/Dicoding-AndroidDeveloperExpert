package com.example.favorite.ui.favorites.helper

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.favorite.database.FavoritedRepository
import com.example.favorite.model.FavoritedStory

class FavoritedAddDeleteViewModel(application: Application) : ViewModel() {
    private val mFavoritedRepository: FavoritedRepository = FavoritedRepository(application)
    fun insert(user: FavoritedStory) {
        mFavoritedRepository.insert(user)
    }

    fun delete(user: FavoritedStory) {
        mFavoritedRepository.delete(user)
    }

    fun isStoryExist(id: String): Boolean {
        return mFavoritedRepository.isStoryExist(id)
    }
}