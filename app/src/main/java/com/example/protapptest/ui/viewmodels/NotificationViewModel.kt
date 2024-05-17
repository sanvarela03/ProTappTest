package com.example.protapptest.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.protapptest.domain.use_cases.notifications.NotificationsUseCases
import com.example.protapptest.ui.events.NotificationsEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationsUseCases: NotificationsUseCases
) : ViewModel() {
    val notifications = notificationsUseCases.getNotifications()

    fun onEvent(event: NotificationsEvent) {
        when (event) {
            is NotificationsEvent.DeleteBtnClick -> {
                deleteNotification(event.id)
            }
        }
    }

    private fun deleteNotification(id: Int) {
        viewModelScope.launch {
            notificationsUseCases.deleteNotification(id)
        }
    }
}