package com.example.protapptest.domain.use_cases.notifications

import com.example.protapptest.data.local.entities.NotificationEntity
import com.example.protapptest.domain.repository.NotificationRepository

class DeleteNotification(
    private val repository: NotificationRepository
) {
    suspend operator fun invoke(notificationId: Int) {
        repository.deleteNotification(notificationId)
    }
}