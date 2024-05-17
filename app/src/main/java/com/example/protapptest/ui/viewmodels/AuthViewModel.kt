package com.example.protapptest.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.protapptest.config.common.UserAuthState
import com.example.protapptest.domain.use_cases.auth.AuthUseCases
import com.example.protapptest.ui.events.SplashEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {
    private val _isAuthenticated = MutableStateFlow<UserAuthState>(UserAuthState.UNKNOWN)
    val isAuthenticated = _isAuthenticated.asStateFlow()

    private var authJob: Job? = null
    fun onEvent(event: SplashEvent) {
        when (event) {
            is SplashEvent.CheckAuthentication -> {
                authJob = viewModelScope.launch(Dispatchers.IO) {
                    val result = authUseCases.authenticate()

                    withContext(Dispatchers.Main) {
                        result.collect {
                            if (it) {
                                _isAuthenticated.emit(UserAuthState.AUTHENTICATED)
                            } else {
                                _isAuthenticated.emit(UserAuthState.UNAUTHENTICATED)
                            }
                        }
                    }

                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        authJob?.let {
            if (it.isActive) {
                it.cancel()
            }
        }
    }
}