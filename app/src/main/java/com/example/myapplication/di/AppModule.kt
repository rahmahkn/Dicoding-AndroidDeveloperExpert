package com.example.myapplication.di

import com.example.core.domain.usecase.StoryInteractor
import com.example.core.domain.usecase.StoryUseCase
import com.example.myapplication.viewmodel.StoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<StoryUseCase> { StoryInteractor(get()) }
}

val storyViewModelModule = module {
    viewModel { StoryViewModel(get()) }
}