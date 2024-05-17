package com.example.protapptest.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.protapptest.ui.states.AddEditProductState

@Entity
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val name: String,
    val description: String,
    val weight: Double,
    val length: Double,
    val width: Double,
    val height: Double,
    val price: Double,
    val unitsAvailable: Int,
    val available: Boolean,
    val producerId: Int
) {
    fun toAddEditProductState(): AddEditProductState {
        return AddEditProductState(
            id = id,
            name = name,
            description = description,
            price = price.toString(),
            weight = weight.toString(),
            units = unitsAvailable.toString(),
            length = length.toString(),
            width = width.toString(),
            height = height.toString(),
            available = available,
        )
    }
}
