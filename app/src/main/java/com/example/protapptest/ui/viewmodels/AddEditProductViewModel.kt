package com.example.protapptest.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.local.entities.ProductEntity
import com.example.protapptest.data.mapper.toProductResponse
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.domain.use_cases.address.AddressUseCases
import com.example.protapptest.domain.use_cases.product.ProductUseCases
import com.example.protapptest.ui.events.AddEditAddressEvent
import com.example.protapptest.ui.events.AddEditProductEvent
import com.example.protapptest.ui.states.AddEditProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class AddEditProductViewModel @Inject constructor(
    private val productUseCases: ProductUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(AddEditProductState())
    var isAttemptingToSave by mutableStateOf(true)
    var showDialog by mutableStateOf(false)
    private val _apiFlow = MutableStateFlow<ApiResponse<MessageResponse>>(ApiResponse.Waiting)
    val apiFlow = _apiFlow.asStateFlow()

    private var currentProductId: Long? = null

    init {
        val productId = savedStateHandle.get<Long>("productId")
        productId?.let {
            if (productId != -1L) {
                isAttemptingToSave = false
                getProduct(it)
            } else {
                isAttemptingToSave = true
            }
        }
    }

    fun onEvent(event: AddEditProductEvent) {
        when (event) {
            is AddEditProductEvent.NameChanged -> {
                state = state.copy(name = event.name)
            }

            is AddEditProductEvent.DescriptionChanged -> {
                state = state.copy(description = event.description)
            }

            is AddEditProductEvent.PriceChanged -> {
                state = state.copy(price = event.price)
            }

            is AddEditProductEvent.WeightChanged -> {
                state = state.copy(weight = event.weight)
            }

            is AddEditProductEvent.UnitsChanged -> {
                state = state.copy(units = event.units)
            }

            is AddEditProductEvent.LengthChanged -> {
                state = state.copy(length = event.length)
            }

            is AddEditProductEvent.WidthChanged -> {
                state = state.copy(width = event.width)
            }

            is AddEditProductEvent.HeightChanged -> {
                state = state.copy(height = event.height)
            }

            is AddEditProductEvent.AvailableChanged -> {
                state = state.copy(available = event.available)
            }

            AddEditProductEvent.SaveBtnClick -> {
                if (isAttemptingToSave) {
                    addProduct()
                } else {
                    updateProduct()
                }
            }
        }
    }

    private fun addProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            productUseCases.addProduct(state.toProductRequest()).collect {
                _apiFlow.value = it
            }
        }
    }

    private fun updateProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            state.id?.let {
                productUseCases.updateProduct(it, state.toUpdateProductRequest())
                    .collect { apiResponse ->
                        _apiFlow.value = apiResponse
                    }
            }
        }
    }

    private fun getProduct(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            productUseCases.getProduct(id)?.also { productEntity ->
                withContext(Dispatchers.Main) {
                    state = productEntity.toAddEditProductState()
                }
            }
        }
    }
}