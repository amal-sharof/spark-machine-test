package com.example.machinetestspark.app.di

import androidx.lifecycle.ViewModel
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
 abstract fun provideLoginRepository(signUpRepositoryImpl: SignUpRepositoryImpl): SignUpRepository
}