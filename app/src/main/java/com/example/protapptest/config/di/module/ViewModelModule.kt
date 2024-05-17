package com.example.protapptest.config.di.module

import com.example.protapptest.domain.use_cases.auth.AuthUseCases
import com.example.protapptest.security.TokenManager
import com.example.protapptest.ui.viewmodels.SignInViewModel
import com.example.protapptest.ui.viewmodels.AuthViewModel
import com.example.protapptest.ui.viewmodels.TokenViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {
    @Provides
    @Singleton
    fun provideSplashViewModel(authUseCases: AuthUseCases): AuthViewModel {
        return AuthViewModel(authUseCases)
    }

    @Provides
    @Singleton
    fun provideSignInViewModel(
        authUseCases: AuthUseCases,
        tokenManager: TokenManager,
        authViewModel: AuthViewModel
    ): SignInViewModel {


        return SignInViewModel(authUseCases, tokenManager, authViewModel)
    }

    @Provides
    @Singleton
    fun provideTokenViewModel(tokenManager: TokenManager): TokenViewModel {
        return TokenViewModel(tokenManager)
    }
}