package com.example.protapptest.data.remote.payload.response.contact

data class ProducerContactInfoResponse(
    val producerId: Long,
    val completeName: String,
    val phone: String,
    val email: String
)
