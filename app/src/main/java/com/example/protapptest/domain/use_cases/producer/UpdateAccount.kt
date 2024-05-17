package com.example.protapptest.domain.use_cases.producer

import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.remote.payload.request.UpdateFirebaseTokenRequest
import com.example.protapptest.data.remote.payload.request.UserInfoRequest
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.domain.repository.ProducerRepository
import kotlinx.coroutines.flow.Flow

class UpdateAccount(
    private val repository: ProducerRepository
) {
    suspend operator fun invoke(
        userInfoRequest: UserInfoRequest
    ): Flow<ApiResponse<MessageResponse>> {
        return repository.updateAccount(userInfoRequest)
    }
}