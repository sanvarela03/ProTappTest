package com.example.protapptest.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.protapptest.config.common.BaseViewModel
import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.domain.use_cases.auth.AuthUseCases
import com.example.protapptest.ui.events.SignUpEvent
import com.example.protapptest.ui.rules.SignUpValidator
import com.example.protapptest.ui.states.SignUpState
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authUseCases: AuthUseCases

) : BaseViewModel() {
    private val TAG = SignUpViewModel::class.simpleName

    var state by mutableStateOf(SignUpState())

    var allValidationsPassed by mutableStateOf(false)

    private val _signUpResponse =
        MutableStateFlow<ApiResponse<MessageResponse>>(ApiResponse.Loading)
    val signUpResponse = _signUpResponse.asStateFlow()

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.FirstNameChanged -> {
                state = state.copy(
                    firstName = event.firstName
                )
                printState()
            }

            is SignUpEvent.LastNameChanged -> {
                state = state.copy(
                    lastName = event.lastName
                )
                printState()
            }

            is SignUpEvent.EmailChanged -> {
                state = state.copy(
                    email = event.email
                )
                printState()
            }

            is SignUpEvent.PhoneChanged -> {
                state = state.copy(phone = event.phone)
                printState()
            }

            is SignUpEvent.PasswordChanged -> {
                state = state.copy(
                    password = event.password
                )
                printState()
            }


            is SignUpEvent.PrivacyPolicyCheckBoxClicked -> {
                state = state.copy(
                    privacyPolicyAccepted = event.status
                )
            }

            is SignUpEvent.UsernameChanged -> {
                state = state.copy(
                    username = event.username
                )
            }

            is SignUpEvent.RegisterButtonClicked -> {
                signUp()
            }


        }
        validateDataWithRules()
    }


    private fun signUp() {
        Log.d(TAG, "Dentro_de_signUpn")
        viewModelScope.launch {
            val tkn = FirebaseMessaging.getInstance().token.await()

            val req = state.toSignUpRequest(tkn)
            Log.d("SignUp", "req $req")
            authUseCases.signUp(req).collect {
                when (it) {
                    is ApiResponse.Failure -> {
                        Log.d("NOPPERSSSSSS :/ ", "message : ${it.errorMessage}")
                    }

                    ApiResponse.Loading -> {

                    }

                    is ApiResponse.Success -> {
                        _signUpResponse.value = it
                        val message = it.data.message
//                        authUseCases.signIn(username = state.username, password = state.password)
                        Log.d("REGISTRADO!!", "message : $message")

                        state.showDialog = true
                    }

                    ApiResponse.Waiting -> {

                    }
                }
            }
        }
    }

    private fun validateDataWithRules() {
        val firstNameResult = SignUpValidator.validateFirstName(state.firstName)
        val lastNameResult = SignUpValidator.validateLastName(state.lastName)
        val usernameResult = SignUpValidator.validateUsername(state.username)
        val phoneResult = SignUpValidator.validatePhone(state.phone)
        val emailResult = SignUpValidator.validateEmail(state.email)
        val passwordResult = SignUpValidator.validatePassword(state.password)
        val privacyPolicyResult = SignUpValidator.validatePrivacyPolicyAcceptance(
            statusValue = state.privacyPolicyAccepted
        )

        Log.d(TAG, "Dentro_de_validateDataWithRules")

        Log.d(TAG, "firstNameResult = ${firstNameResult}")
        Log.d(TAG, "lastNameResult = ${lastNameResult}")
        Log.d(TAG, "usernameResult = ${usernameResult}")
        Log.d(TAG, "emailNameResult = ${emailResult}")
        Log.d(TAG, "passwordResult = ${passwordResult}")
        Log.d(TAG, "privacyPolicyResult= $privacyPolicyResult")

        state = state.copy(
            firstNameError = firstNameResult.status,
            lastNameError = lastNameResult.status,
            usernameError = usernameResult.status,
            emailError = emailResult.status,
            phoneError = phoneResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyResult.status
        )
        allValidationsPassed =
            firstNameResult.status &&
                    lastNameResult.status &&
                    usernameResult.status &&
                    emailResult.status &&
                    phoneResult.status &&
                    passwordResult.status &&
                    privacyPolicyResult.status


        Log.d(TAG, "allValidationsPassed= $allValidationsPassed")
    }

    private fun printState() {
        Log.d(TAG, "Dentro_de_printState")
        Log.d(TAG, state.toString())
    }
}