package com.example.protapptest.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.protapptest.ui.screens.SignInScreen
import com.example.protapptest.ui.screens.SignUpScreen
import com.example.protapptest.ui.screens.TermsAndConditionsScreen

@Composable
fun AuthNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SignInScreen.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Screen.SignInScreen.route) {
//            SignInScreen(navController = navController)
        }
        composable(Screen.SignUpScreen.route) {
            SignUpScreen(navController = navController)
        }
        composable(Screen.TermsAndConditionsScreen.route) {
            TermsAndConditionsScreen()
        }
    }
}