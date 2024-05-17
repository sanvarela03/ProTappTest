package com.example.protapptest.config.di.module

import com.example.protapptest.data.local.dao.CustomerInfoDao
import com.example.protapptest.data.local.dao.DeliveryAddressDao
import com.example.protapptest.data.local.dao.NotificationDao
import com.example.protapptest.data.local.dao.OrderDao
import com.example.protapptest.data.local.dao.PickupAddressDao
import com.example.protapptest.data.local.dao.ProducerDao
import com.example.protapptest.data.local.dao.ProducerInfoDao
import com.example.protapptest.data.local.dao.StatusDao
import com.example.protapptest.data.local.dao.TransporterInfoDao
import com.example.protapptest.data.remote.api.AddressApi
import com.example.protapptest.data.remote.api.AuthApi
import com.example.protapptest.data.remote.api.ProducerApi
import com.example.protapptest.data.remote.api.ProductApi
import com.example.protapptest.data.repository.AddressRepositoryImpl
import com.example.protapptest.data.repository.AuthRepositoryImpl
import com.example.protapptest.data.repository.NotificationRepositoryImpl
import com.example.protapptest.data.repository.OrderRepositoryImpl
import com.example.protapptest.data.repository.ProducerRepositoryImpl
import com.example.protapptest.data.repository.ProductRepositoryImpl
import com.example.protapptest.domain.repository.AddressRepository
import com.example.protapptest.domain.repository.AuthRepository
import com.example.protapptest.domain.repository.NotificationRepository
import com.example.protapptest.domain.repository.OrderRepository
import com.example.protapptest.domain.repository.ProducerRepository
import com.example.protapptest.domain.repository.ProductRepository
import com.example.protapptest.security.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi, tokenManager: TokenManager): AuthRepository {
        return AuthRepositoryImpl(api, tokenManager)
    }

    @Provides
    @Singleton
    fun provideProducerRepository(
        api: ProducerApi,
        producerDao: ProducerDao,
        tokenManager: TokenManager
    ): ProducerRepository {
        return ProducerRepositoryImpl(api, producerDao, tokenManager)
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        api: ProductApi,
        producerDao: ProducerDao,
        tokenManager: TokenManager
    ): ProductRepository {
        return ProductRepositoryImpl(
            api,
            producerDao,
            tokenManager
        )
    }

    @Provides
    @Singleton
    fun provideAddressRepository(
        api: AddressApi,
        producerDao: ProducerDao
    ): AddressRepository {
        return AddressRepositoryImpl(api, producerDao)
    }

    @Provides
    @Singleton
    fun provideNotificationRepository(
        dao: NotificationDao
    ): NotificationRepository {
        return NotificationRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideOrderRepository(
        tokenManager: TokenManager,
        producerApi: ProducerApi,
        orderDao: OrderDao,
        customerInfoDao: CustomerInfoDao,
        producerInfoDao: ProducerInfoDao,
        pickupAddressDao: PickupAddressDao,
        deliveryAddressDao: DeliveryAddressDao,
        transporterInfoDao: TransporterInfoDao,
        statusDao: StatusDao

    ): OrderRepository {
        return OrderRepositoryImpl(
            tokenManager = tokenManager,
            producerApi = producerApi,
            orderDao = orderDao,
            customerInfoDao = customerInfoDao,
            producerInfoDao = producerInfoDao,
            pickupAddressDao = pickupAddressDao,
            deliveryAddressDao = deliveryAddressDao,
            transporterInfoDao = transporterInfoDao,
            statusDao = statusDao
        )
    }
}