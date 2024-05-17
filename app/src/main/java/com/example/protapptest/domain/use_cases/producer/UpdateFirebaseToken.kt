package com.example.protapptest.domain.use_cases.producer

import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.remote.payload.request.UpdateFirebaseTokenRequest
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.data.remote.payload.response.ProducerInfoResponse
import com.example.protapptest.domain.repository.ProducerRepository
import kotlinx.coroutines.flow.Flow

class UpdateFirebaseToken(
    private val repository: ProducerRepository
) {
    suspend operator fun invoke(
        firebaseToken: String
    ): Flow<ApiResponse<MessageResponse>> {
        return repository.updateFirebaseToken(UpdateFirebaseTokenRequest(firebaseToken))
    }
}