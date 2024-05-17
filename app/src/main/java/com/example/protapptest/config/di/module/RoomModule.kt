package com.example.protapptest.config.di.module

import android.app.Application
import androidx.room.Room
import com.example.protapptest.data.local.dao.CustomerInfoDao
import com.example.protapptest.data.local.dao.DeliveryAddressDao
import com.example.protapptest.data.local.dao.NotificationDao
import com.example.protapptest.data.local.dao.OrderDao
import com.example.protapptest.data.local.dao.PickupAddressDao
import com.example.protapptest.data.local.dao.ProducerDao
import com.example.protapptest.data.local.dao.ProducerInfoDao
import com.example.protapptest.data.local.dao.StatusDao
import com.example.protapptest.data.local.dao.TransporterInfoDao
import com.example.protapptest.data.local.db.ProducerInfoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    @Singleton
    fun provideProducerInfoDatabase(app: Application): ProducerInfoDatabase {
        return Room.databaseBuilder(
            app,
            ProducerInfoDatabase::class.java,
            "producerinfodb.db"
        ).build()
    }


    @Provides
    @Singleton
    fun provideCustomerInfoDao(db: ProducerInfoDatabase): CustomerInfoDao {
        return db.customerInfoDao
    }

    @Provides
    @Singleton
    fun provideDeliveryAddressDao(db: ProducerInfoDatabase): DeliveryAddressDao {
        return db.deliveryAddressDao
    }

    @Provides
    @Singleton
    fun provideNotificationDao(db: ProducerInfoDatabase): NotificationDao {
        return db.notificationDao
    }

    @Provides
    @Singleton
    fun provideOrderDao(db: ProducerInfoDatabase): OrderDao {
        return db.orderDao
    }

    @Provides
    @Singleton
    fun providePickupAddressDao(db: ProducerInfoDatabase): PickupAddressDao {
        return db.pickupAddressDao
    }

    @Provides
    @Singleton
    fun provideProducerDao(db: ProducerInfoDatabase): ProducerDao {
        return db.producerDao
    }

    @Provides
    @Singleton
    fun provideProducerInfoDao(db: ProducerInfoDatabase): ProducerInfoDao {
        return db.producerInfoDao
    }

    @Provides
    @Singleton
    fun provideStatusDao(db: ProducerInfoDatabase): StatusDao {
        return db.statusDao
    }

    @Provides
    @Singleton
    fun provideTransporterInfoDao(db: ProducerInfoDatabase): TransporterInfoDao {
        return db.transporterInfoDao
    }
}