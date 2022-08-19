package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Story(

    val id: String,
    val photoUrl: String,
    val createdAt: String,
    val name: String,
    val description: String

) : Parcelable