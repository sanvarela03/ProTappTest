package com.example.protapptest.domain.repository

import com.example.protapptest.data.remote.payload.request.SignInRequest
import com.example.protapptest.data.remote.payload.request.SignUpRequest
import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.data.remote.payload.response.SignInResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signIn(signInRequest: SignInRequest): Flow<ApiResponse<SignInResponse>>
    fun signOut(): Flow<ApiResponse<MessageResponse>>
    fun signUp(signUpRequest: SignUpRequest): Flow<ApiResponse<MessageResponse>>
    fun authenticate(): Flow<Boolean>
}