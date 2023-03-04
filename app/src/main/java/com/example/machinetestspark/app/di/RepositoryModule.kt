package com.example.machinetestspark.app.di

import androidx.lifecycle.ViewModel
import com.example.machinetestspark.dashboard.data.repository.DashboardRepositoryImpl
import com.example.machinetestspark.dashboard.domain.repository.DashboardRepository
import com.example.machinetestspark.login.data.repository.LoginRepositoryImpl
import com.example.machinetestspark.login.domain.repository.LoginRepository
import com.example.machinetestspark.signup.data.repository.SignUpRepositoryImpl
import com.example.machinetestspark.signup.domain.repository.SignUpRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
@Binds
 abstract fun provideSignUpRepository(signUpRepositoryImpl: SignUpRepositoryImpl): SignUpRepository

 @Binds
 abstract fun provideLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

 @Binds
 abstract fun provideDashboardRepository(dashboardRepositoryImpl: DashboardRepositoryImpl): DashboardRepository
}