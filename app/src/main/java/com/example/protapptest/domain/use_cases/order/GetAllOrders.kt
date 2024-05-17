package com.example.protapptest.domain.use_cases.order

import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.local.entities.relations.OrderAndStatus
import com.example.protapptest.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow

class GetAllOrders(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(fetchFromRemote: Boolean): Flow<ApiResponse<List<OrderAndStatus>>> {
        return repository.getAllOrdersWithStatus(fetchFromRemote)
    }
}