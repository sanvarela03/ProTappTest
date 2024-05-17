package com.example.protapptest.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.protapptest.config.common.UserAuthState
import com.example.protapptest.ui.events.SplashEvent
import com.example.protapptest.ui.navigation.Screen
import com.example.protapptest.ui.navigation.graphs.Graph
import com.example.protapptest.ui.viewmodels.AuthViewModel

@Composable
fun RootScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    navigateTo: (String) -> Unit,
) {
    LaunchedEffect(true) {
        authViewModel.onEvent(SplashEvent.CheckAuthentication)
    }
    val userState = authViewModel.isAuthenticated.collectAsState().value

    when (userState) {
        UserAuthState.UNKNOWN -> {
            navigateTo(Screen.SplashScreen.route)
        }

        UserAuthState.UNAUTHENTICATED -> {
            navigateTo(Graph.AUTHENTICATION)
        }

        UserAuthState.AUTHENTICATED -> {
            navigateTo(Screen.HomeScreen.route)
        }
    }
}