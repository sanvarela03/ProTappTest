package com.example.protapptest.ui.navigation.graphs

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.protapptest.ui.navigation.Screen
import com.example.protapptest.ui.screens.AddEditAddressScreen
import com.example.protapptest.ui.screens.AddressListScreen
import com.example.protapptest.ui.screens.ProductsScreen

fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.HOME,
        startDestination = Screen.ProductsScreen.route
    ) {
        composable(
            route = Screen.ProductsScreen.route
        ) {
            ProductsScreen(
                viewModel = hiltViewModel(),
                navigateTo = { navController.navigate(it) }
            )
        }
        composable(
            route = Screen.AddressListScreen.route
        ) {
            AddressListScreen(
                navigateTo = { navController.navigate(it) },
                homeViewModel = hiltViewModel()
            )
        }
        navigation(
            route = Graph.DETAILS,
            startDestination = Screen.AddEditAddressScreen.route
        ) {
            composable(
                route = Screen.AddEditAddressScreen.route
            ) {
                AddEditAddressScreen(
                    navigateTo = { navController.navigate(it) }
                )
            }
        }
    }
}