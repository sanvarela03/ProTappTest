package com.example.protapptest.data.remote.payload.request

data class ProductRequest(
    val name: String,
    val description: String,
    val price: Double,
    val currency: String = "COP",
    val weightPerUnit_kg: Double,
    val length_cm: Double,
    val width_cm: Double,
    val height_cm: Double,
    val unitsAvailable: Int,
    val available: Boolean,
)
