package com.example.protapptest.domain.use_cases.auth

import com.example.protapptest.data.remote.payload.request.SignInRequest
import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.remote.payload.response.SignInResponse
import com.example.protapptest.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class SignIn(
    private val repository: AuthRepository
) {
    operator fun invoke(username: String, password: String): Flow<ApiResponse<SignInResponse>> {
        return repository.signIn(SignInRequest(username, password))
    }
}