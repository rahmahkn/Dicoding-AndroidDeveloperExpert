package com.example.myapplication.network

import com.example.myapplication.helper.DomainRetrofit
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideMoshiConverter(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @DomainRetrofit
    @Singleton
    @Provides
    fun provideDomainRetrofit(moshi: Moshi): Retrofit.Builder = Retrofit.Builder()
        .baseUrl(DOMAIN_API_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))


    private const val DOMAIN_API_URL = "https://story-api.dicoding.dev/v1/"
}