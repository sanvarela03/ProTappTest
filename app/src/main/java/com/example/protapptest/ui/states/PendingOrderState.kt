package com.example.protapptest.ui.states

import com.example.protapptest.data.local.entities.relations.OrderAndStatus

data class PendingOrderState(
    val orderList: List<OrderAndStatus> = emptyList(),
    var isRefreshing: Boolean = false,
    var msgResponse: String = ""
)