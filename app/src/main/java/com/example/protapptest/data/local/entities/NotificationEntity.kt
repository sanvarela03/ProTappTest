package com.example.protapptest.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
class NotificationEntity(
    @PrimaryKey
    val id: Int?,
    val title: String,
    val body: String,
    val createdAt: String
)