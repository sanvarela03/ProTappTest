package com.example.protapptest.domain.repository

import com.example.protapptest.data.local.entities.NotificationEntity
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    suspend fun saveNotification(notificationEntity: NotificationEntity)
    suspend fun deleteNotification(notificationId: Int)
    fun getNotifications(): Flow<List<NotificationEntity>>
}