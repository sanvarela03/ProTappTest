package com.example.protapptest.data.remote.payload.request

data class UpdateProductRequest(
    val name: String,
    val description: String,
    val price: Double,
    val unitsAvailable: Int,
    val weight: Double,
    val length: Double,
    val width: Double,
    val height: Double,
    val available: Boolean
)
