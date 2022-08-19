package com.example.core.di

import androidx.room.Room
import com.dicoding.tourismapp.core.data.StoryRepository
import com.dicoding.tourismapp.core.domain.repository.IStoryRepository
import com.dicoding.tourismapp.core.utils.AppExecutors
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.local.room.FavoritedDatabase
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<FavoritedDatabase>().favoritedDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            FavoritedDatabase::class.java, "Story.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://story-api.dicoding.dev/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IStoryRepository> { StoryRepository(get(), get(), get()) }
}
