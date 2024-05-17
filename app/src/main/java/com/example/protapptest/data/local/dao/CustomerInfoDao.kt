package com.example.protapptest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.protapptest.data.local.entities.CustomerInfoEntity

@Dao
interface CustomerInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomerInfoEntity(customerInfoEntity: CustomerInfoEntity)

    @Transaction
    @Query("SELECT * FROM CustomerInfoEntity WHERE customerId = :customerId")
    suspend fun getCustomerInfo(customerId: Long): CustomerInfoEntity

    @Transaction
    @Query("DELETE FROM CustomerInfoEntity")
    suspend fun clearCustomerInfoEntity()

}