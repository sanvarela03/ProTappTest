package com.example.protapptest.domain.use_cases.notifications

import com.example.protapptest.data.local.entities.NotificationEntity
import com.example.protapptest.domain.repository.NotificationRepository
import com.example.protapptest.domain.repository.ProductRepository

class SaveNotification(
    private val repository: NotificationRepository
) {
    suspend operator fun invoke(notificationEntity: NotificationEntity) {
        repository.saveNotification(notificationEntity)
    }
}