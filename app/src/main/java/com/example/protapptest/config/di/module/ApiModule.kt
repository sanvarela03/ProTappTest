package com.example.protapptest.config.di.module

import com.example.protapptest.data.remote.api.AddressApi
import com.example.protapptest.data.remote.api.AuthApi
import com.example.protapptest.data.remote.api.ProducerApi
import com.example.protapptest.data.remote.api.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    @Singleton
    fun provideAuthAPI(
        okHttpClient: OkHttpClient,
        retrofit: Retrofit.Builder
    ): AuthApi =
        retrofit.client(okHttpClient).build().create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideProducerApi(
        okHttpClient: OkHttpClient,
        retrofit: Retrofit.Builder
    ): ProducerApi {
        return retrofit.client(okHttpClient).build().create(ProducerApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductApi(
        okHttpClient: OkHttpClient,
        retrofit: Retrofit.Builder
    ): ProductApi {
        return retrofit.client(okHttpClient).build().create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAddressApi(
        okHttpClient: OkHttpClient,
        retrofit: Retrofit.Builder
    ): AddressApi {
        return retrofit.client(okHttpClient).build().create(AddressApi::class.java)
    }
}