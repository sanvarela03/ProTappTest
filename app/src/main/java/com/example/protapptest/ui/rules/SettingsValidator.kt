package com.example.protapptest.ui.rules

object SettingsValidator {
    fun validateFirstName(firstName: String): ValidationResult {
        return ValidationResult(
            (!firstName.isNullOrEmpty() && firstName.length >= 1)
        )
    }

    fun validateLastName(lastName: String): ValidationResult {
        return ValidationResult(
            (!lastName.isNullOrEmpty() && lastName.length >= 1)
        )
    }

    fun validateEmail(email: String): ValidationResult {
        return ValidationResult(
            (!email.isNullOrEmpty())
        )
    }

    fun validatePhone(phone: String): ValidationResult {
        return ValidationResult(
            (!phone.isNullOrEmpty())
        )
    }

    fun validateUsername(username: String): ValidationResult {
        return ValidationResult(
            (!username.isNullOrEmpty())
        )
    }

    fun validatePassword(password: String): ValidationResult {
        return ValidationResult(
            (!password.isNullOrEmpty() && password.length >= 4)
        )
    }
}