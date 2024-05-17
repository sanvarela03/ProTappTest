package com.example.protapptest.ui.states

import com.example.protapptest.data.remote.payload.request.UserInfoRequest

data class SettingsState(
    var userId: Long = -1L,
    var username: String = "",
    var email: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var phone: String = "",
    //var password: String = "",

    var usernameError: Boolean = false,
    var emailError: Boolean = false,
    var firstNameError: Boolean = false,
    var lastNameError: Boolean = false,
    var phoneError: Boolean = false,
    //var passwordError : Boolean = false,
) {
    fun toUserInfoRequest(): UserInfoRequest {
        return UserInfoRequest(
            username = username,
            name = firstName,
            lastname = lastName,
            email = email,
            phone = phone,
        )
    }
}
