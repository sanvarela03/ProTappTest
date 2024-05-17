package com.example.protapptest.ui.events

sealed class OrderListEvent {
    data class CheckProductsBtnClick(val id: Long) : OrderListEvent()
    object Refresh : OrderListEvent()
}