package com.example.protapptest.domain.use_cases.address

import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.remote.payload.request.AddressRequest
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.domain.repository.AddressRepository
import kotlinx.coroutines.flow.Flow

class AddAddress(
    private val repository: AddressRepository
) {
    suspend operator fun invoke(addressRequest: AddressRequest): Flow<ApiResponse<MessageResponse>> {
        return repository.addNewAddress(addressRequest)
    }
}