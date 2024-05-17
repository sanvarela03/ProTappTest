package com.example.protapptest.ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.protapptest.ui.navigation.Screen
import com.example.protapptest.ui.screens.AddEditAddressScreen
import com.example.protapptest.ui.screens.AddEditProductScreen

fun NavGraphBuilder.productDetailGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.PRODUCT_DETAIL,
        startDestination = Screen.AddEditProductScreen.route
    ) {
        composable(
            route = Screen.AddEditProductScreen.route + "?productId={productId}",
            arguments = listOf(
                navArgument(name = "productId") {
                    type = NavType.LongType
                    defaultValue = -1
                }
            )
        ) {
            AddEditProductScreen(
                navigateTo = { navHostController.navigate(it) }
            )
        }
    }
}