package com.example.protapptest.ui.states

data class SignInUIState(
    var username: String = "",
    var password: String = "",

    var usernameError: Boolean = false,
    var passwordError: Boolean = false
)
