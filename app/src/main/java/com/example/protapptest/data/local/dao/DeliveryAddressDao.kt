package com.example.protapptest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.protapptest.data.local.entities.DeliveryAddressEntity

@Dao
interface DeliveryAddressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeliveryAddress(deliveryAddressEntity: DeliveryAddressEntity)

    @Transaction
    @Query("SELECT * FROM DeliveryAddressEntity WHERE orderId = :orderId")
    suspend fun getDeliveryAddress(orderId: Long): DeliveryAddressEntity?

    @Transaction
    @Query("DELETE FROM DeliveryAddressEntity")
    suspend fun clearDeliveryAddressEntity()
}