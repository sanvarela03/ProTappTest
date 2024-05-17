package com.example.protapptest.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.protapptest.data.remote.payload.response.contact.ProducerContactInfoResponse

@Entity
data class ProducerInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val producerId: Long,
    val completeName: String,
    val phone: String,
    val email: String,
    val orderId: Long
) {
    fun toProducerContactInfoResponse(): ProducerContactInfoResponse {
        return ProducerContactInfoResponse(
            producerId = producerId,
            completeName = completeName,
            phone = phone,
            email = email,
        )
    }
}