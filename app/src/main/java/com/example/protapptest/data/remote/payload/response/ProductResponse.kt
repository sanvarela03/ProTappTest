package com.example.protapptest.data.remote.payload.response

import com.example.protapptest.data.local.entities.ProductEntity

data class ProductResponse(
    val id: Long,
    val name: String,
    val description: String,
    val weight: Double,
    val length: Double,
    val width: Double,
    val height: Double,
    val price: Double,
    val unitsAvailable: Int,
    val available: Boolean,
    val producerId: Int
) {
    fun toProductEntity(): ProductEntity {
        return ProductEntity(
            id = id,
            name = name,
            description = description,
            weight = weight,
            length = length * 100,
            width = width * 100,
            height = height * 100,
            price = price,
            unitsAvailable = unitsAvailable,
            available = available,
            producerId = producerId,
        )
    }
}