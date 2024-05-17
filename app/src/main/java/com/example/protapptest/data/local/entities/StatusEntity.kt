package com.example.protapptest.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.protapptest.data.remote.payload.response.order.StatusResponse

@Entity
data class StatusEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val statusId: Long?,
    val name: String,
    val createdAt: String,
    val orderId: Long
) {
    fun toStatusResponse(): StatusResponse {
        return StatusResponse(
            statusId = statusId,
            name = name,
            createdAt = createdAt,
            orderId = orderId
        )
    }
}
