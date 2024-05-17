package com.example.protapptest.domain.use_cases.product

import com.example.protapptest.data.local.entities.ProductEntity
import com.example.protapptest.domain.repository.ProductRepository

class GetProduct(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id: Long): ProductEntity? {
        return repository.getProduct(id)
    }
}