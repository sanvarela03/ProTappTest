package com.example.protapptest.data.remote.payload.response

data class AddressResponse(
    val id: Long,
    val name: String,
    val street: String,
    val instruction: String,
    val latitude: Double,
    val longitude: Double,
    val city: String,
    val state: String,
    val country: String,
    val userId : Long
)
