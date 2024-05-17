package com.example.protapptest.ui.states

import com.example.protapptest.data.remote.payload.request.ProductRequest
import com.example.protapptest.data.remote.payload.request.UpdateProductRequest

data class AddEditProductState(
    var id: Long? = null,
    var name: String = "",
    var description: String = "",
    var price: String = "",
    var weight: String = "",
    var units: String = "",
    var length: String = "",
    var width: String = "",
    var height: String = "",
    var available: Boolean = false
) {
    fun toProductRequest(): ProductRequest {
        return ProductRequest(
            name = name,
            description = description,
            price = price.toDouble(),
            weightPerUnit_kg = weight.toDouble(),
            length_cm = length.toDouble(),
            width_cm = width.toDouble(),
            height_cm = height.toDouble(),
            unitsAvailable = units.toInt(),
            available = available
        )
    }

    fun toUpdateProductRequest(): UpdateProductRequest {
        return UpdateProductRequest(
            name = name,
            description = description,
            price = price.toDouble(),
            weight = weight.toDouble(),
            length = length.toDouble(),
            width = width.toDouble(),
            height = height.toDouble(),
            unitsAvailable = units.toInt(),
            available = available,
        )
    }
}
