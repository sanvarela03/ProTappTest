package com.example.protapptest.domain.use_cases.product

import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.remote.payload.request.UpdateProductRequest
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class UpdateProduct(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(
        productId: Long,
        updateProductRequest: UpdateProductRequest
    ): Flow<ApiResponse<MessageResponse>> {
        return repository.updateProduct(productId, updateProductRequest)
    }
}