package com.example.favorite.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.core.data.source.local.entity.FavoritedStory
import com.example.core.data.source.local.repository.FavoritedRepository

class FavoritedViewModel(application: Application) : ViewModel() {
    private val mFavoritedRepository: FavoritedRepository = FavoritedRepository(application)

    fun getAllFavorites(): LiveData<List<FavoritedStory>> = mFavoritedRepository.getAllFavorites()

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