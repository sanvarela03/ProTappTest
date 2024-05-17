package com.example.protapptest.data.remote.payload.request

data class UserInfoRequest(
    val username: String,
    val name: String,
    val lastname: String,
    val email: String,
    val phone: String,
)
