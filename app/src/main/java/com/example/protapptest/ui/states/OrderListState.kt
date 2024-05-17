package com.example.protapptest.ui.states

import com.example.protapptest.data.local.entities.relations.OrderAndStatus

data class OrderListState(
    val orderList: List<OrderAndStatus> = emptyList(),
    val isRefreshing: Boolean = false,
)