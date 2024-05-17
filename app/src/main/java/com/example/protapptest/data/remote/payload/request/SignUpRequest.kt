package com.example.protapptest.data.remote.payload.request

data class SignUpRequest(
    val username: String,
    val name: String,
    val lastName: String,
    val email: String,
    val phone: String = "NaN",
    val firebaseToken: String,
    val password: String,
    private val role: List<String> = listOf("user", "prod")
)