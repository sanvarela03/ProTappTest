package com.example.protapptest.data.remote.payload.response

data class StateResponse(
    val id: Long,
    val name: String,
    val initials: String,
    val country: CountryResponse
)
