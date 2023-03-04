package com.example.machinetestspark.app.di

import android.app.Application
import com.example.machinetestspark.app.datastore.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatastore(application: Application) = DataStoreManager(application)
}