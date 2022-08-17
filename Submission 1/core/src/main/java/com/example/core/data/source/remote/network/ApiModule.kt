package com.example.core.data.source.remote.network

import com.example.myapplication.helper.DomainRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideApiService(@DomainRetrofit retrofit: Retrofit.Builder): ApiService =
        retrofit.build().create(ApiService::class.java)
}