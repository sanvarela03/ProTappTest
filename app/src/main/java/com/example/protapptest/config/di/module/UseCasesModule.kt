package com.example.protapptest.config.di.module

import com.example.protapptest.domain.repository.AddressRepository
import com.example.protapptest.domain.repository.AuthRepository
import com.example.protapptest.domain.repository.NotificationRepository
import com.example.protapptest.domain.repository.OrderRepository
import com.example.protapptest.domain.repository.ProducerRepository
import com.example.protapptest.domain.repository.ProductRepository
import com.example.protapptest.domain.use_cases.address.AddAddress
import com.example.protapptest.domain.use_cases.address.AddressUseCases
import com.example.protapptest.domain.use_cases.address.DeleteAddress
import com.example.protapptest.domain.use_cases.address.GetAddress
import com.example.protapptest.domain.use_cases.address.GetCurrentAddressId
import com.example.protapptest.domain.use_cases.address.UpdateAddress
import com.example.protapptest.domain.use_cases.auth.AuthUseCases
import com.example.protapptest.domain.use_cases.auth.Authenticate
import com.example.protapptest.domain.use_cases.auth.SignIn
import com.example.protapptest.domain.use_cases.auth.SignOut
import com.example.protapptest.domain.use_cases.auth.SignUp
import com.example.protapptest.domain.use_cases.notifications.DeleteNotification
import com.example.protapptest.domain.use_cases.notifications.GetNotifications
import com.example.protapptest.domain.use_cases.notifications.NotificationsUseCases
import com.example.protapptest.domain.use_cases.notifications.SaveNotification
import com.example.protapptest.domain.use_cases.order.AcceptOrder
import com.example.protapptest.domain.use_cases.order.GetAllOrders
import com.example.protapptest.domain.use_cases.order.OrderUseCases
import com.example.protapptest.domain.use_cases.producer.GetLocalProducer
import com.example.protapptest.domain.use_cases.producer.GetProducer
import com.example.protapptest.domain.use_cases.producer.ProducerUseCases
import com.example.protapptest.domain.use_cases.producer.UpdateAccount
import com.example.protapptest.domain.use_cases.producer.UpdateFirebaseToken
import com.example.protapptest.domain.use_cases.product.AddProduct
import com.example.protapptest.domain.use_cases.product.DeleteProduct
import com.example.protapptest.domain.use_cases.product.GetProduct
import com.example.protapptest.domain.use_cases.product.GetProducts
import com.example.protapptest.domain.use_cases.product.ProductUseCases
import com.example.protapptest.domain.use_cases.product.UpdateProduct
import com.example.protapptest.service.MyFirebaseMessagingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Provides
    @Singleton
    fun provideAuthUseCases(repository: AuthRepository): AuthUseCases {
        return AuthUseCases(
            signIn = SignIn(repository),
            signOut = SignOut(repository),
            signUp = SignUp(repository),
            authenticate = Authenticate(repository)
        )
    }

    @Provides
    @Singleton
    fun provideProducerUseCases(repository: ProducerRepository): ProducerUseCases {
        return ProducerUseCases(
            getProducer = GetProducer(repository),
            getLocalProducer = GetLocalProducer(repository),
            updateFirebaseToken = UpdateFirebaseToken(repository),
            updateAccount = UpdateAccount(repository)
        )
    }

    @Provides
    @Singleton
    fun provideProductUsesCases(repository: ProductRepository): ProductUseCases {
        return ProductUseCases(
            getProducts = GetProducts(repository),
            addProduct = AddProduct(repository),
            getProduct = GetProduct(repository),
            updateProduct = UpdateProduct(repository),
            deleteProduct = DeleteProduct(repository)
        )
    }

    @Provides
    @Singleton
    fun provideAddressUseCases(repository: AddressRepository): AddressUseCases {
        return AddressUseCases(
            addAddress = AddAddress(repository),
            updateAddress = UpdateAddress(repository),
            getAddress = GetAddress(repository),
            getCurrentAddressId = GetCurrentAddressId(repository),
            deleteAddress = DeleteAddress(repository)
        )
    }

    @Provides
    @Singleton
    fun provideNotificationsUseCases(repository: NotificationRepository): NotificationsUseCases {
        return NotificationsUseCases(
            saveNotification = SaveNotification(repository),
            getNotifications = GetNotifications(repository),
            deleteNotification = DeleteNotification(repository)
        )
    }

//    @Provides
//    @Singleton
//    fun provideMyFirebaseMessagingService(notificationsUseCases: NotificationsUseCases): MyFirebaseMessagingService {
//        return MyFirebaseMessagingService(notificationsUseCases)
//    }

    @Provides
    @Singleton
    fun provideOrderUseCases(repository: OrderRepository): OrderUseCases {
        return OrderUseCases(
            getAllOrders = GetAllOrders(repository),
            acceptOrder = AcceptOrder(repository)
        )
    }
}