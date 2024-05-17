package com.example.protapptest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.protapptest.data.local.entities.StatusEntity
import com.example.protapptest.data.local.entities.relations.OrderAndStatus

@Dao
interface StatusDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStatus(statusEntity: StatusEntity)

    @Transaction
    @Query("SELECT * FROM OrderEntity")
    suspend fun getOrderAndStatus(): List<OrderAndStatus>?

    @Transaction
    @Query("DELETE FROM StatusEntity")
    suspend fun clearStatusEntity()
}