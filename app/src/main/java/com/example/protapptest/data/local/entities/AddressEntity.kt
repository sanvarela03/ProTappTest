package com.example.protapptest.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.protapptest.data.remote.payload.response.CityResponse
import com.example.protapptest.ui.states.AddEditAddressState

@Entity
data class AddressEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val name: String,
    val street: String,
    val instruction: String,
    val latitude: Double,
    val longitude: Double,
    val city: String,
    val state: String,
    val country: String,
    val userId: Long
) {
    fun toAddEditAddressState(isCurrentAddress: Boolean): AddEditAddressState {
        return AddEditAddressState(
            name = name,
            country = country,
            state = state,
            city = city,
            street = street,
            instruction = instruction,
            isCurrentAddress = isCurrentAddress,
            latitude = latitude.toString(),
            longitude = longitude.toString(),
        )
    }
}
