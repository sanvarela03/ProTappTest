package com.example.protapptest.data.remote.payload.response

data class ProducerInfoResponse(
    val producerId: Long,
    val username: String,
    val name: String,
    val lastname: String,
    val email: String,
    val phone: String,
    val currentAddressId: Long,
    val addressList: List<AddressResponse> = emptyList(),
    val productsList: List<ProductResponse> = emptyList()
)
