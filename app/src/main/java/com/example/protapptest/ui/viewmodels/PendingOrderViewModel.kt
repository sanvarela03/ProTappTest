package com.example.protapptest.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.domain.use_cases.order.OrderUseCases
import com.example.protapptest.ui.events.PendingOrderEvent
import com.example.protapptest.ui.states.OrderListState
import com.example.protapptest.ui.states.PendingOrderState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PendingOrderViewModel @Inject constructor(
    private val orderUseCases: OrderUseCases
) : ViewModel() {

    var state by mutableStateOf(PendingOrderState())

    var showDialog by mutableStateOf(false)

    init {
        getAllOrders()
    }

    fun onEvent(event: PendingOrderEvent) {
        when (event) {
            is PendingOrderEvent.AcceptBtnClick -> {
                val orderId = event.orderId
                acceptOrder(orderId)

            }

            PendingOrderEvent.Refresh -> {
                getAllOrders(fetchFromRemote = true)
            }
        }
    }


    private fun acceptOrder(orderId: Long) {
        viewModelScope.launch {
            orderUseCases.acceptOrder(orderId).collect {
                when (it) {
                    ApiResponse.Waiting -> {}
                    ApiResponse.Loading -> {}
                    is ApiResponse.Failure -> {}
                    is ApiResponse.Success -> {
                        state = state.copy(msgResponse = it.data.message)
                        showDialog = true
                    }
                }
            }
        }
    }

    private fun getAllOrders(fetchFromRemote: Boolean = false) {
        viewModelScope.launch {
            orderUseCases.getAllOrders(fetchFromRemote).collect {
                when (it) {
                    ApiResponse.Waiting -> {}
                    ApiResponse.Loading -> {}
                    is ApiResponse.Failure -> {}
                    is ApiResponse.Success -> {

                        val orderList = it.data.filter { o ->
                            !o.statusList.any { s -> s.statusId == 2L }
                        }
                        state = state.copy(orderList = orderList)
                    }
                }
            }
        }
    }
}