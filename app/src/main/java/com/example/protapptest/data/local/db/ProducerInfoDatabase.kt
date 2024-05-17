package com.example.protapptest.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.protapptest.data.local.dao.CustomerInfoDao
import com.example.protapptest.data.local.dao.DeliveryAddressDao
import com.example.protapptest.data.local.dao.NotificationDao
import com.example.protapptest.data.local.dao.OrderDao
import com.example.protapptest.data.local.dao.PickupAddressDao
import com.example.protapptest.data.local.dao.ProducerDao
import com.example.protapptest.data.local.dao.ProducerInfoDao
import com.example.protapptest.data.local.dao.StatusDao
import com.example.protapptest.data.local.dao.TransporterInfoDao
import com.example.protapptest.data.local.entities.AddressEntity
import com.example.protapptest.data.local.entities.CustomerInfoEntity
import com.example.protapptest.data.local.entities.DeliveryAddressEntity
import com.example.protapptest.data.local.entities.NotificationEntity
import com.example.protapptest.data.local.entities.OrderEntity
import com.example.protapptest.data.local.entities.PickupAddressEntity
import com.example.protapptest.data.local.entities.ProducerEntity
import com.example.protapptest.data.local.entities.ProducerInfoEntity
import com.example.protapptest.data.local.entities.ProductEntity
import com.example.protapptest.data.local.entities.StatusEntity
import com.example.protapptest.data.local.entities.TransporterInfoEntity

@Database(
    entities = [
        AddressEntity::class,
        CustomerInfoEntity::class,
        DeliveryAddressEntity::class,
        NotificationEntity::class,
        OrderEntity::class,
        PickupAddressEntity::class,
        ProducerEntity::class,
        ProducerInfoEntity::class,
        ProductEntity::class,
        StatusEntity::class,
        TransporterInfoEntity::class,
    ],
    version = 1
)
abstract class ProducerInfoDatabase : RoomDatabase() {
    abstract val customerInfoDao: CustomerInfoDao
    abstract val deliveryAddressDao: DeliveryAddressDao
    abstract val notificationDao: NotificationDao
    abstract val orderDao: OrderDao
    abstract val pickupAddressDao: PickupAddressDao
    abstract val producerDao: ProducerDao
    abstract val producerInfoDao: ProducerInfoDao
    abstract val statusDao: StatusDao
    abstract val transporterInfoDao: TransporterInfoDao
}