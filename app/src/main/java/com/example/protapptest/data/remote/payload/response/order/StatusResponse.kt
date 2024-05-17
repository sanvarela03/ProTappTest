package com.example.protapptest.data.remote.payload.response.order

import com.example.protapptest.data.local.entities.StatusEntity

//import com.example.compapptest.data.local.entities.StatusEntity

data class StatusResponse(
    val statusId: Long?,
    val name: String,
    val createdAt: String,
    val orderId: Long
) {
    fun toStatusEntity(): StatusEntity {
        return StatusEntity(
            id = null,
            statusId = statusId,
            name = name,
            createdAt = createdAt,
            orderId = orderId
        )
    }
}
