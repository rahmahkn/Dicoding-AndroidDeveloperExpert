package com.example.myapplication.di

import com.dicoding.tourismapp.core.domain.usecase.StoryInteractor
import com.dicoding.tourismapp.core.domain.usecase.StoryUseCase
import com.example.core.ui.FavoritedViewModel
import com.example.core.ui.StoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<StoryUseCase> { StoryInteractor(get()) }
}

val viewModelModule = module {
    viewModel { StoryViewModel(get()) }
    viewModel { FavoritedViewModel(get()) }
}