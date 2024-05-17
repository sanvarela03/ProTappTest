package com.example.protapptest.data.remote.payload.response

data class RefreshResponse(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String
)