package com.example.protapptest.config.di.module

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.protapptest.config.common.HOST_URL
import com.example.protapptest.security.AuthAuthenticator
import com.example.protapptest.security.AuthInterceptor
import com.example.protapptest.security.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_store")

fun String.toCoinFormat() = replace(Regex("(\\d)(?=(\\d{3})+(?!\\d))"), "$1 ")
@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        authAuthenticator: AuthAuthenticator,
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient
            .Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .authenticator(authAuthenticator)
            .build()
    }

    @Provides
    @Singleton
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager =
        TokenManager(context)

    @Provides
    @Singleton
    fun provideAuthInterceptor(tokenManager: TokenManager): AuthInterceptor =
        AuthInterceptor(tokenManager)

    @Provides
    @Singleton
    fun provideAuthAuthenticator(tokenManager: TokenManager): AuthAuthenticator =
        AuthAuthenticator(tokenManager)

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder =
        Retrofit.Builder().baseUrl(HOST_URL).addConverterFactory(GsonConverterFactory.create())

//    @Singleton
//    @Provides
//    fun providesAuthRepository(auth: AuthDataSource): AuthRepository {
//        return AuthRepository(authDataSource)
//    }

//    @Singleton
//    @Provides
//    fun provideSavedStateHandle(@ApplicationContext context: Context): SavedStateHandle =
//        SavedStateHandleController.createHandle(context)



//    @Provides
//    @Singleton
//    fun provideViewModelScope(viewModel: ViewModel): CoroutineScope {
//        return viewModel.viewModelScope
//    }
}