package com.example.protapptest.domain.repository

import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.local.entities.ProducerEntity
import com.example.protapptest.data.remote.payload.request.UpdateFirebaseTokenRequest
import com.example.protapptest.data.remote.payload.request.UserInfoRequest
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.data.remote.payload.response.ProducerInfoResponse
import kotlinx.coroutines.flow.Flow

interface ProducerRepository {
    fun loadProducer(
        fetchFromRemote: Boolean,
        id: Long
    ): Flow<ApiResponse<ProducerInfoResponse>>

    fun getProducer(id: Long): Flow<ProducerEntity>

    suspend fun updateAccount(userInfoRequest: UserInfoRequest): Flow<ApiResponse<MessageResponse>>
    suspend fun updateFirebaseToken(
        request: UpdateFirebaseTokenRequest
    ): Flow<ApiResponse<MessageResponse>>
}