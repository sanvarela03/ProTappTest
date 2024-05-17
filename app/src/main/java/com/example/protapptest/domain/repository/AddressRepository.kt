package com.example.protapptest.domain.repository

import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.local.entities.AddressEntity
import com.example.protapptest.data.remote.payload.request.AddressRequest
import com.example.protapptest.data.remote.payload.request.UpdateAddressRequest
import com.example.protapptest.data.remote.payload.response.MessageResponse
import kotlinx.coroutines.flow.Flow

interface AddressRepository {
    suspend fun addNewAddress(addressRequest: AddressRequest): Flow<ApiResponse<MessageResponse>>
    suspend fun updateAddress(updateAddressRequest: UpdateAddressRequest): Flow<ApiResponse<MessageResponse>>
    suspend fun deleteAddress(addressId: Long): Flow<ApiResponse<MessageResponse>>
     fun getAddressById(id: Long): Flow<AddressEntity>
    suspend fun getCurrentAddressId(producerId: Long): Long?
}