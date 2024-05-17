package com.example.protapptest.data.remote.payload.response.contact

data class TransporterContactInfoResponse(
    val transporterId: Long?,
    val completeName: String?,
    val phone: String?,
    val email: String?
)