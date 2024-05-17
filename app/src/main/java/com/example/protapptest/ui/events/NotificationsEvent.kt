package com.example.protapptest.ui.events

sealed class NotificationsEvent {
    data class DeleteBtnClick(val id: Int) : NotificationsEvent()
}