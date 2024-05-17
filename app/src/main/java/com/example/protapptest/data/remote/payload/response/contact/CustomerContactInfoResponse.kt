package com.example.protapptest.data.remote.payload.response.contact

data class CustomerContactInfoResponse(
    val customerId: Long,
    val completeName: String,
    val phone: String,
    val email: String
)