package com.example.core.ui

import androidx.lifecycle.ViewModel
import com.dicoding.tourismapp.core.domain.usecase.StoryUseCase

//@HiltViewModel
//class StoryViewModel @Inject constructor(
//    private val storyRepository: AbstractStoryRepository
//) : ViewModel() {
//
//    fun postLogin(email: String, password: String) =
//        storyRepository.postLogin(email, password)
//
//    fun getStories(authHeader: String) =
//        storyRepository.getStories(authHeader)
//}

class StoryViewModel(val storyUseCase: StoryUseCase) : ViewModel() {
    fun postLogin(email: String, password: String) =
        storyUseCase.postLogin(email, password)

    fun getStories(authHeader: String) =
        storyUseCase.getStories(authHeader)
}