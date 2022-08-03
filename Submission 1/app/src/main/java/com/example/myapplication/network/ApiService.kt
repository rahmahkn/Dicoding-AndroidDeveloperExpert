package com.example.myapplication.network

import com.example.myapplication.model.domain.GetStoryResponse
import com.example.myapplication.model.domain.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("stories")
    fun getStories(
        @Header("Authorization") authHeader: String,
    ): Call<GetStoryResponse>
}