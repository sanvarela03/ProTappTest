package com.example.protapptest.ui.events

sealed class SettingsEvent {
    data class FirstNameChanged(val firstName: String) : SettingsEvent()
    data class LastNameChanged(val lastName: String) : SettingsEvent()
    data class UsernameChanged(val username: String) : SettingsEvent()
    data class EmailChanged(val email: String) : SettingsEvent()
    data class PhoneChanged(val phone: String) : SettingsEvent()
    data class PasswordChanged(val password: String) : SettingsEvent()
    object UpdateButtonClicked : SettingsEvent()
    object DeleteButtonClicked : SettingsEvent()
}