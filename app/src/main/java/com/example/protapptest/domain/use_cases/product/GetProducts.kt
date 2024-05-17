package com.example.protapptest.domain.use_cases.product

import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.local.entities.ProductEntity
import com.example.protapptest.data.remote.payload.response.ProductsResponse
import com.example.protapptest.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProducts(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(fetchFromRemote: Boolean): Flow<ApiResponse<List<ProductEntity>>> {
        return repository.getProducts(fetchFromRemote)
    }
}