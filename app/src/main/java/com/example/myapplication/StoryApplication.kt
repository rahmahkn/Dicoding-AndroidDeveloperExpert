package com.example.myapplication

import android.app.Application
import com.example.core.di.databaseModule
import com.example.core.di.networkModule
import com.example.core.di.repositoryModule
import com.example.myapplication.di.storyViewModelModule
import com.example.myapplication.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class StoryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@StoryApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    storyViewModelModule
                )
            )
        }
    }
}