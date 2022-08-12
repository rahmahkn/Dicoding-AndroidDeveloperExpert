package com.example.favorite.ui.favorites.helper

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.favorite.database.FavoritedRepository
import com.example.favorite.model.FavoritedStory

class MainViewModel(application: Application) : ViewModel() {
    private val mFavoritedRepository: FavoritedRepository = FavoritedRepository(application)
    fun getAllFavorites(): LiveData<List<FavoritedStory>> = mFavoritedRepository.getAllFavorites()
}