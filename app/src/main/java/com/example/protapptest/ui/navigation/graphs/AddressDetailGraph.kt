package com.example.protapptest.ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.protapptest.ui.navigation.Screen
import com.example.protapptest.ui.screens.AddEditAddressScreen

fun NavGraphBuilder.addressDetailGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.ADDRESS_DETAIL,
        startDestination = Screen.AddEditAddressScreen.route
    ) {
        composable(
            route = Screen.AddEditAddressScreen.route + "?addressId={addressId}",
            arguments = listOf(
                navArgument(name = "addressId") {
                    type = NavType.LongType
                    defaultValue = -1
                }
            )
        ) {
            AddEditAddressScreen(
                navigateTo = { navHostController.navigate(it) })
        }
    }
}