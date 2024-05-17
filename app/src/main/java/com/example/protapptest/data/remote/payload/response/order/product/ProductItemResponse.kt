package com.example.protapptest.data.remote.payload.response.order.product

data class ProductItemResponse(
    val productId: Long,
    val name: String,
    val description: String,
    val units: Int,
    val price: Double,
)
