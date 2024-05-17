package com.example.protapptest.domain.use_cases.address

import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.remote.payload.request.UpdateAddressRequest
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.domain.repository.AddressRepository
import kotlinx.coroutines.flow.Flow

class DeleteAddress(
    private val repository: AddressRepository
) {
    suspend operator fun invoke(addressId: Long): Flow<ApiResponse<MessageResponse>> {
        return repository.deleteAddress(addressId)
    }
}