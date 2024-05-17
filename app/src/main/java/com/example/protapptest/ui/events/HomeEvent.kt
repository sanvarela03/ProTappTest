package com.example.protapptest.ui.events

sealed class HomeEvent {
    object SignOutBtnClicked : HomeEvent()
    object Refresh : HomeEvent()
}