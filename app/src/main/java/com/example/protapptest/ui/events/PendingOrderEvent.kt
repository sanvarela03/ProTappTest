package com.example.protapptest.ui.events

sealed class PendingOrderEvent {
    object Refresh : PendingOrderEvent()
    data class AcceptBtnClick(val orderId: Long) : PendingOrderEvent()
}