package com.example.protapptest.domain.repository

import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.local.entities.relations.OrderAndStatus
import com.example.protapptest.data.remote.payload.response.MessageResponse
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    suspend fun getAllOrdersWithStatus(
        fetchFromRemote: Boolean
    ): Flow<ApiResponse<List<OrderAndStatus>>>

    suspend fun acceptOrder(
        orderId: Long
    ): Flow<ApiResponse<MessageResponse>>
}