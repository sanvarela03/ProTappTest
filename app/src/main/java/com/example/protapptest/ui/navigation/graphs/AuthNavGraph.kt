package com.example.protapptest.ui.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.protapptest.ui.navigation.Screen
import com.example.protapptest.ui.screens.SignInScreen
import com.example.protapptest.ui.screens.SignUpScreen
import com.example.protapptest.ui.screens.SplashScreen
import com.example.protapptest.ui.screens.TermsAndConditionsScreen


fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = Screen.SignInScreen.route
    ) {

        composable(
            route = Screen.SignInScreen.route
        ) {
            SignInScreen(navController = navController)
        }
        composable(
            route = Screen.SignUpScreen.route
        ) {
            SignUpScreen(navController = navController)
        }

        composable(
            route = Screen.TermsAndConditionsScreen.route
        ) {
            TermsAndConditionsScreen()
        }
    }
}