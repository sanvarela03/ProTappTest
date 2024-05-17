package com.example.protapptest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.protapptest.data.local.entities.OrderEntity
import com.example.protapptest.data.local.entities.relations.OrderAndStatus

@Dao
interface OrderDao {
    @Insert
    suspend fun insertOrder(orderEntity: OrderEntity)

    @Transaction
    @Query("SELECT * FROM OrderEntity")
    suspend fun getOrderAndStatus(): List<OrderAndStatus>?

    @Transaction
    @Query("SELECT O.* FROM OrderEntity O WHERE O.orderId = :orderI")
    suspend fun getOrder(orderI: Long): OrderEntity

    @Transaction
    @Query("DELETE FROM OrderEntity")
    suspend fun clearOrderEntity()


}