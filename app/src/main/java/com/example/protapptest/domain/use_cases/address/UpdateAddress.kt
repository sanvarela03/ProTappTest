package com.example.protapptest.domain.use_cases.address

import android.util.Log
import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.remote.payload.request.AddressRequest
import com.example.protapptest.data.remote.payload.request.UpdateAddressRequest
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.domain.repository.AddressRepository
import kotlinx.coroutines.flow.Flow

class UpdateAddress(
    private val repository: AddressRepository
) {
    suspend operator fun invoke(updateAddressRequest: UpdateAddressRequest): Flow<ApiResponse<MessageResponse>> {
        Log.d("UpdateAddress", "updateAddressRequest = $updateAddressRequest")
        return repository.updateAddress(updateAddressRequest)
    }
}