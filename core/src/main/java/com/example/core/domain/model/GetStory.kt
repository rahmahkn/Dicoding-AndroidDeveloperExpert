package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetStoryModel(

    val listStory: List<Story>,
    val error: Boolean,
    val message: String
) : Parcelable