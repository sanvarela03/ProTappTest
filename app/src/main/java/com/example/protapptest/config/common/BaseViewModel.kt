package com.example.protapptest.config.common

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel
@Inject
constructor() : ViewModel() {
    private var mJob: Job? = null

    protected fun <T> baseRequest(
        mutableStateFlow: MutableState<T>,
        errorHandler: CoroutinesErrorHandler,
        request: () -> Flow<T>
    ) {
        mJob = viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, error ->
            viewModelScope.launch(Dispatchers.Main) {
                errorHandler.onError(
                    message = error.localizedMessage
                        ?: "Ocurrió un error! Por favor intenta de nuevo."
                )
            }
        }) {
            request().collect {
                withContext(Dispatchers.Main) {
                    mutableStateFlow.value = it
                }
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        mJob?.let {
            if (it.isActive) {
                it.cancel()
            }
        }
    }
}

interface CoroutinesErrorHandler {
    fun onError(message: String)
}