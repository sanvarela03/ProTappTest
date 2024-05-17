package com.example.protapptest.domain.use_cases.notifications

import com.example.protapptest.data.local.entities.NotificationEntity
import com.example.protapptest.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow

class GetNotifications(
    private val repository: NotificationRepository
) {
    operator fun invoke(): Flow<List<NotificationEntity>> {
        return repository.getNotifications()
    }
}