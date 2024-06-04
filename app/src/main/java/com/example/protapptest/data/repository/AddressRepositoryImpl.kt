package com.example.protapptest.data.repository

import android.util.Log
import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.config.common.apiRequestFlow
import com.example.protapptest.data.local.dao.ProducerDao
import com.example.protapptest.data.local.entities.AddressEntity
import com.example.protapptest.data.local.entities.relations.ProducerAndAddress
import com.example.protapptest.data.remote.api.AddressApi
import com.example.protapptest.data.remote.payload.request.AddressRequest
import com.example.protapptest.data.remote.payload.request.UpdateAddressRequest
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.domain.repository.AddressRepository
import com.example.protapptest.security.TokenManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddressRepositoryImpl @Inject constructor(
    val api: AddressApi,
    val dao: ProducerDao,
    private val tokenManager: TokenManager
) : AddressRepository {
    override suspend fun addNewAddress(addressRequest: AddressRequest): Flow<ApiResponse<MessageResponse>> {
        return apiRequestFlow { api.addAddress(addressRequest) }
    }

    override suspend fun updateAddress(updateAddressRequest: UpdateAddressRequest): Flow<ApiResponse<MessageResponse>> {
        Log.d("AddressRepositoryImpl", "updateAddressRequest =$updateAddressRequest")
        val userId = tokenManager.getUserId().first()
        return apiRequestFlow {
            api.updateAddress(
                userId = userId ?: -1L,
                addressId = updateAddressRequest.id,
                updateAddressRequest = updateAddressRequest
            )
        }
    }

    override suspend fun deleteAddress(addressId: Long): Flow<ApiResponse<MessageResponse>> {
        return apiRequestFlow {
            api.deleteAddress(addressId)
        }
    }

    override fun getAddressById(id: Long): Flow<AddressEntity> {
        return dao.getAddress(id)
    }

    override suspend fun getCurrentAddressId(producerId: Long): Long? {
        return dao.getCurrentAddressId(producerId)
    }
}