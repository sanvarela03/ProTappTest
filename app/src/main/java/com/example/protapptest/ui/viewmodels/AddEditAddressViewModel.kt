package com.example.protapptest.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.local.entities.AddressEntity
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.domain.use_cases.address.AddressUseCases
import com.example.protapptest.domain.use_cases.producer.ProducerUseCases
import com.example.protapptest.ui.events.AddEditAddressEvent
import com.example.protapptest.ui.states.AddEditAddressState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddEditAddressViewModel @Inject constructor(
    private val addressUseCases: AddressUseCases,
    private val producerUseCases: ProducerUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var isAttemptingToSave by mutableStateOf(true)

    private var currentAddressId: Long? = null

    var state by mutableStateOf(AddEditAddressState())

    private val _apiFlow = MutableStateFlow<ApiResponse<MessageResponse>>(ApiResponse.Waiting)
    val apiFlow = _apiFlow.asStateFlow()


    var showDialog by mutableStateOf(false)


    init {
        val addressId = savedStateHandle.get<Long>("addressId")
        addressId?.let { addressId ->
            if (addressId != -1L) {
                isAttemptingToSave = false
                currentAddressId = addressId
                viewModelScope.launch {
                    addressUseCases.getAddress(addressId).collect { address ->
                        producerUseCases.getLocalProducer(address.userId).collect { producer ->
                            if (producer != null) {
                                state =
                                    address.toAddEditAddressState(producer.currentAddressId == address.id)
                            }
                        }
                    }
                }

            } else {
                isAttemptingToSave = true
            }
        }
    }

    fun onEvent(event: AddEditAddressEvent) {
        when (event) {
            is AddEditAddressEvent.NameChanged -> {
                state = state.copy(name = event.name)
            }

            is AddEditAddressEvent.InstructionChanged -> {
                state = state.copy(instruction = event.instruction)
            }

            is AddEditAddressEvent.StreetChanged -> {
                state = state.copy(street = event.street)
            }

            is AddEditAddressEvent.CityChanged -> {
                state = state.copy(city = event.city)
            }

            is AddEditAddressEvent.StateChanged -> {
                state = state.copy(state = event.state)
            }

            is AddEditAddressEvent.CountryChanged -> {
                state = state.copy(country = event.country)
            }

            is AddEditAddressEvent.LatitudeChanged -> {
                state = state.copy(latitude = event.latitude)
            }

            is AddEditAddressEvent.LongitudeChanged -> {
                state = state.copy(longitude = event.longitude)
            }

            is AddEditAddressEvent.IsCurrentAddressChanged -> {
                state = state.copy(isCurrentAddress = event.isCurrentAddress)
            }

            AddEditAddressEvent.SaveBtnClick -> {
                if (isAttemptingToSave) {
                    saveAddress()
                } else {
                    updateAddress()
                }
            }
        }
    }

    private fun saveAddress() {
        viewModelScope.launch(Dispatchers.IO) {
            addressUseCases.addAddress(state.toAddressRequest()).collect {
                withContext(Dispatchers.Main) {
                    _apiFlow.value = it
                }
            }
        }
    }

    private fun updateAddress() {
        viewModelScope.launch(Dispatchers.IO) {
            if (currentAddressId != null) {
                addressUseCases.updateAddress(state.toUpdateAddressRequest(currentAddressId!!))
                    .collect {
                        _apiFlow.value = it
                    }
            }
        }
    }
}