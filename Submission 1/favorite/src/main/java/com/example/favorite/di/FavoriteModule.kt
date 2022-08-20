package com.example.favorite.di

import com.example.favorite.viewmodel.FavoritedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritedViewModelModule = module {
    viewModel { FavoritedViewModel(get()) }
}