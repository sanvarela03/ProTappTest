package com.example.protapptest.domain.use_cases.auth

import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class SignOut(
    private val repository: AuthRepository
) {
    operator fun invoke(): Flow<ApiResponse<MessageResponse>> {
        return repository.signOut()
    }
}