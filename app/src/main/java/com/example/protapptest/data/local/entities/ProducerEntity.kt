package com.example.protapptest.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.protapptest.data.remote.payload.response.AddressResponse
import com.example.protapptest.data.remote.payload.response.ProductResponse
import com.example.protapptest.ui.states.SettingsState

@Entity
data class ProducerEntity(
    @PrimaryKey(autoGenerate = false)
    val producerId: Long,
    val username: String,
    val name: String,
    val lastname: String,
    val email: String,
    val phone: String,
    val currentAddressId: Long
) {
    fun toSettingsState(): SettingsState {
        return SettingsState(
            username = username,
            firstName = name,
            lastName = lastname,
            email = email,
            phone = phone
        )
    }
}
