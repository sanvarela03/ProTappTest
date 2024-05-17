package com.example.protapptest.ui.events

sealed class AddEditProductEvent {
    data class NameChanged(val name: String) : AddEditProductEvent()
    data class DescriptionChanged(val description: String) : AddEditProductEvent()
    data class PriceChanged(val price: String) : AddEditProductEvent()
    data class WeightChanged(val weight: String) : AddEditProductEvent()
    data class UnitsChanged(val units: String) : AddEditProductEvent()
    data class LengthChanged(val length: String) : AddEditProductEvent()
    data class WidthChanged(val width: String) : AddEditProductEvent()
    data class HeightChanged(val height: String) : AddEditProductEvent()
    data class AvailableChanged(val available: Boolean) : AddEditProductEvent()

    object SaveBtnClick : AddEditProductEvent()
}