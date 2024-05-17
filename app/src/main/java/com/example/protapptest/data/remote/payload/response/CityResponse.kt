package com.example.protapptest.data.remote.payload.response

data class CityResponse(
    val id: Long,
    val name: String,
    val state: StateResponse
)