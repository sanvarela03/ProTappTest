package com.example.protapptest.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.remote.payload.response.AddressResponse
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.domain.use_cases.address.AddressUseCases
import com.example.protapptest.ui.events.AddressListEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressListViewModel @Inject constructor(
    private val addressUseCases: AddressUseCases,
) : ViewModel() {
    private val _apiFlow = MutableStateFlow<ApiResponse<MessageResponse>>(ApiResponse.Waiting)
    val apiFlow = _apiFlow.asStateFlow()


    var showDialog by mutableStateOf(false)
    var id by mutableStateOf(-1L)
    var currentAddress by mutableStateOf<AddressResponse?>(null)

    fun onEvent(event: AddressListEvent) {
        when (event) {
            is AddressListEvent.DeleteBtnClick -> {
                deleteAddress(event.id)
            }
        }
    }

    private fun deleteAddress(id: Long) {
        viewModelScope.launch {
            addressUseCases.deleteAddress(id).collect {
                _apiFlow.value = it
            }
        }
    }
}