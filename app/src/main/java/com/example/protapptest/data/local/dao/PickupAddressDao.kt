package com.example.protapptest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.protapptest.data.local.entities.PickupAddressEntity

@Dao
interface PickupAddressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPickupAddress(pickupAddressEntity: PickupAddressEntity)
    @Transaction
    @Query("SELECT * FROM PickupAddressEntity WHERE orderId = :orderId")
    suspend fun getPickupAddress(orderId: Long): PickupAddressEntity?

    @Transaction
    @Query("DELETE FROM PickupAddressEntity")
    suspend fun clearPickupAddressEntity()
}