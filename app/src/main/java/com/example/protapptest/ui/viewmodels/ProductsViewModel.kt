package com.example.protapptest.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.domain.use_cases.product.ProductUseCases
import com.example.protapptest.ui.events.ProductsEvent
import com.example.protapptest.ui.states.ProductsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productUseCases: ProductUseCases
) : ViewModel() {

    var state by mutableStateOf(ProductsState())

    var showDeleteDialog by mutableStateOf(false)
    var showResponseDialog by mutableStateOf(false)

    private var getProductsJob: Job? = null

    init {
        getProducts()
    }

    fun onEvent(event: ProductsEvent) {
        when (event) {
            is ProductsEvent.DeleteBtnClick -> {
                Log.d("ProductsViewModel", "deletebtnclick : ${event.id}")
                deleteProduct(event.id)
            }

            ProductsEvent.Refresh -> {
                getProducts(fetchFromRemote = true)
            }
        }
    }

    private fun deleteProduct(productId: Long) {
        viewModelScope.launch {
            productUseCases.deleteProduct(productId).collect { response ->

                when (response) {
                    ApiResponse.Waiting -> {}
                    ApiResponse.Loading -> {}
                    is ApiResponse.Failure -> {}
                    is ApiResponse.Success -> {
                        showResponseDialog = true
                        state.msgResponse = response.data.message
                    }
                }
            }
        }
    }

    fun getProducts(fetchFromRemote: Boolean = false) {
        getProductsJob?.let {
            if (it.isActive) {
                it.cancel()
            }
        }

        getProductsJob = viewModelScope.launch(Dispatchers.IO) {
            productUseCases.getProducts(fetchFromRemote).collect {
                when (it) {
                    is ApiResponse.Failure -> {}
                    ApiResponse.Loading -> {}
                    is ApiResponse.Success -> {
                        withContext(Dispatchers.Main) {
                            it.data.let {
                                state = state.copy(
                                    products = it
                                )
                            }
                        }
                    }

                    ApiResponse.Waiting -> {}
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("ProductsViewModel", "onCleared")

        getProductsJob?.let {
            if (it.isActive) {
                it.cancel()
            }
        }
    }
}