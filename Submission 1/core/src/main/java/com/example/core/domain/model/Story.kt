package com.dicoding.tourismapp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Story(

    val id: String,
    val photoUrl: String,
    val createdAt: String,
    val name: String,
    val description: String,

    ) : Parcelable