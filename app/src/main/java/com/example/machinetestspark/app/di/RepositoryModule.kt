package com.example.machinetestspark.app.di

import com.example.machinetestspark.dashboard.data.repository.DashboardRepositoryImpl
import com.example.machinetestspark.dashboard.domain.repository.DashboardRepository
import com.example.machinetestspark.auth.data.repository.AuthRepositoryImpl
import com.example.machinetestspark.auth.domain.repository.AuthRepository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideLoginRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun provideDashboardRepository(dashboardRepositoryImpl: DashboardRepositoryImpl): DashboardRepository
}