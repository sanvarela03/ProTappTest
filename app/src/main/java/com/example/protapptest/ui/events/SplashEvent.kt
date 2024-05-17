package com.example.protapptest.ui.events

sealed class SplashEvent {
    object CheckAuthentication : SplashEvent()
}