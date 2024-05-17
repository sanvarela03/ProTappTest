package com.example.protapptest.ui.events

sealed class AddressListEvent {
    data class DeleteBtnClick(val id: Long) : AddressListEvent()
}