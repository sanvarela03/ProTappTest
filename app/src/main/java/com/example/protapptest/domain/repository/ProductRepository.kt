package com.example.protapptest.domain.repository

import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.local.entities.ProductEntity
import com.example.protapptest.data.remote.payload.request.ProductRequest
import com.example.protapptest.data.remote.payload.request.UpdateProductRequest
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.data.remote.payload.response.ProductResponse
import com.example.protapptest.data.remote.payload.response.ProductsResponse
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(fetchFromRemote: Boolean): Flow<ApiResponse<List<ProductEntity>>>
    suspend fun addNewProduct(productRequest: ProductRequest): Flow<ApiResponse<MessageResponse>>
    suspend fun getProduct(id: Long): ProductEntity?
    suspend fun deleteProduct(id: Long): Flow<ApiResponse<MessageResponse>>
    suspend fun updateProduct(
        productId: Long,
        updateProductRequest: UpdateProductRequest
    ): Flow<ApiResponse<MessageResponse>>
}