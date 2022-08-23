package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginModel(

    val loginResult: LoginResultModel,
    val error: Boolean,
    val message: String
) : Parcelable

@Parcelize
data class LoginResultModel(

    val name: String,
    val userId: String,
    val token: String

) : Parcelable