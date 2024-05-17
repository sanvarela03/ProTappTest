package com.example.protapptest.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.local.entities.ProducerEntity
import com.example.protapptest.domain.repository.ProducerRepository
import com.example.protapptest.domain.use_cases.producer.ProducerUseCases
import com.example.protapptest.security.TokenManager
import com.example.protapptest.ui.events.SettingsEvent
import com.example.protapptest.ui.rules.SettingsValidator
import com.example.protapptest.ui.states.SettingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val producerUseCases: ProducerUseCases,
    private val tokenManager: TokenManager,
    private val producerRepository: ProducerRepository
) : ViewModel() {

    var state by mutableStateOf(SettingsState())
    val userId = tokenManager.getUserId()
    var allValidationsPassed by mutableStateOf(false)


    var showDialog by mutableStateOf(false)
    var msgResponse by mutableStateOf("")

    init {
        viewModelScope.launch {

            val userId = tokenManager.getUserId().first()
            val p = getProducer(userId).first()

            state = p.toSettingsState()
            state = state.copy(userId = userId ?: -1L)
        }
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.UsernameChanged -> {
                state = state.copy(username = event.username)
            }

            is SettingsEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }

            is SettingsEvent.FirstNameChanged -> {
                state = state.copy(firstName = event.firstName)
            }

            is SettingsEvent.LastNameChanged -> {
                state = state.copy(lastName = event.lastName)
            }

            is SettingsEvent.PasswordChanged -> {
            }

            is SettingsEvent.PhoneChanged -> {
                state = state.copy(phone = event.phone)
            }

            SettingsEvent.UpdateButtonClicked -> {
                updateAccount()
            }

            SettingsEvent.DeleteButtonClicked -> {
                deleteAccount()
            }
        }
        validateStateWithRules()
    }

    private fun deleteAccount() {

    }

    private fun updateAccount() {
        viewModelScope.launch {
            producerUseCases.updateAccount(state.toUserInfoRequest()).collect {
                when (it) {
                    ApiResponse.Waiting -> {}
                    ApiResponse.Loading -> {}
                    is ApiResponse.Failure -> {}
                    is ApiResponse.Success -> {
                        showDialog = true
                        msgResponse = it.data.message
                    }
                }
            }
        }
    }

    private fun validateStateWithRules() {
        val v1 = SettingsValidator.validateUsername(state.username).status
        val v2 = SettingsValidator.validateEmail(state.email).status
        val v3 = SettingsValidator.validateFirstName(state.firstName).status
        val v4 = SettingsValidator.validateLastName(state.lastName).status
        val v5 = SettingsValidator.validatePhone(state.phone).status

        state = state.copy(
            usernameError = v1,
            emailError = v2,
            firstNameError = v3,
            lastNameError = v4,
            phoneError = v5
        )

        allValidationsPassed = v1 && v2 && v3 && v4 && v5

    }

    fun getProducer(userId: Long?): Flow<ProducerEntity> {
        return producerUseCases.getLocalProducer(userId ?: -1L)
    }
}