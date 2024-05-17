package com.example.protapptest.domain.use_cases.product

import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.remote.payload.request.ProductRequest
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class AddProduct(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(productRequest: ProductRequest): Flow<ApiResponse<MessageResponse>> {
        return repository.addNewProduct(productRequest)
    }
}