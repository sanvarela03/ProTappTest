package com.example.protapptest.domain.use_cases.auth

import com.example.protapptest.data.remote.payload.request.SignUpRequest
import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class SignUp(
    private val repository: AuthRepository
) {
    operator fun invoke(signUpRequest: SignUpRequest): Flow<ApiResponse<MessageResponse>> {
        return repository.signUp(signUpRequest)
    }
}