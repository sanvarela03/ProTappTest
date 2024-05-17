package com.example.protapptest.app

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.protapptest.config.common.UserAuthState
import com.example.protapptest.ui.events.SplashEvent
import com.example.protapptest.ui.navigation.Screen
import com.example.protapptest.ui.navigation.graphs.Graph
import com.example.protapptest.ui.navigation.graphs.authNavGraph
import com.example.protapptest.ui.screens.HomeScreen
import com.example.protapptest.ui.screens.SplashScreen
import com.example.protapptest.ui.viewmodels.AuthViewModel
import com.google.firebase.messaging.FirebaseMessaging

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProducerApp(
    authViewModel: AuthViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        authViewModel.onEvent(SplashEvent.CheckAuthentication)
    }
    val navController = rememberNavController()
    val userState = authViewModel.isAuthenticated.collectAsState().value

    NavHost(
        navController = navController,
        startDestination =
        when (userState) {
            UserAuthState.UNKNOWN -> {
                Screen.SplashScreen.route
            }

            UserAuthState.UNAUTHENTICATED -> {
                Graph.AUTHENTICATION
            }

            UserAuthState.AUTHENTICATED -> {
                Screen.HomeScreen.route
            }
        }

    ) {
        authNavGraph(navController)
        composable(Screen.HomeScreen.route) {
            HomeScreen()
        }
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
    }
}

private fun notification() {
    FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->

        if (!task.isSuccessful) {
            Log.w("FCM", "Error al traer el token de registro", task.exception)
            return@addOnCompleteListener
        }
        val token = task.result


        val msg = "El token es = $token"
        Log.d("FCM", msg)
    }
}
