package com.example.protapptest.ui.events

sealed class ProductsEvent {
    data class DeleteBtnClick(val id: Long) : ProductsEvent()
    object Refresh : ProductsEvent()
}