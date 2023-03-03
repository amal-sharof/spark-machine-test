package com.example.machinetestspark.app.di

import androidx.lifecycle.ViewModel
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
 abstract fun provideLoginRepository(loginRepository: LoginRepository): LoginRepository
}