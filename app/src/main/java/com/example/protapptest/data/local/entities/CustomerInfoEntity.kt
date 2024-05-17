package com.example.protapptest.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.protapptest.data.remote.payload.response.contact.CustomerContactInfoResponse

@Entity
data class CustomerInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val customerId: Long,
    val completeName: String,
    val phone: String,
    val email: String,
    val orderId: Long
) {
    fun toCustomerContactInfoResponse(): CustomerContactInfoResponse {
        return CustomerContactInfoResponse(
            customerId = customerId,
            completeName = completeName,
            phone = phone,
            email = email,
        )
    }
}
