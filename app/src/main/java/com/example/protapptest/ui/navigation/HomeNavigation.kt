package com.example.protapptest.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.protapptest.ui.navigation.graphs.Graph
import com.example.protapptest.ui.navigation.graphs.addressDetailGraph
import com.example.protapptest.ui.navigation.graphs.detailsNavGraph
import com.example.protapptest.ui.screens.AddEditAddressScreen
import com.example.protapptest.ui.screens.AddEditProductScreen
import com.example.protapptest.ui.screens.AddressListScreen
import com.example.protapptest.ui.screens.NotificationsScreen
import com.example.protapptest.ui.screens.OrderListScreen
import com.example.protapptest.ui.screens.PendingOrderScreen
import com.example.protapptest.ui.screens.ProductsScreen
import com.example.protapptest.ui.screens.SettingsScreen
import com.example.protapptest.ui.states.HomeState
import com.example.protapptest.ui.viewmodels.HomeViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeNavigation(
    homeViewModel: HomeViewModel = hiltViewModel(),
    homeNavController: NavHostController,
) {
    NavHost(
        route = Graph.HOME,
        navController = homeNavController,
        startDestination = Screen.ProductsScreen.route
    ) {
        composable(Screen.ProductsScreen.route) {
            ProductsScreen(
                viewModel = hiltViewModel(),
                navigateTo = { homeNavController.navigate(it) }
            )
        }
        composable(Screen.AddressListScreen.route) {
            AddressListScreen(
                homeViewModel = homeViewModel,
                navigateTo = {
                    homeNavController.navigate(it)
                }
            )
        }

        composable(Screen.NotificationsScreen.route) {
            NotificationsScreen()

        }
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
                navigateTo = {
                    homeNavController.navigate(it) {
                        popUpTo(it) {
                            inclusive = true
                        }
                    }
                }
            )
        }

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
                navigateTo = {
                    homeNavController.navigate(it) {
                        popUpTo(it) {
                            inclusive = true
                        }
                    }
                }
            )
        }
//        addressDetailGraph(homeNavController)

        composable(Screen.PendingOrdersScreen.route) {
            PendingOrderScreen()
        }

        composable(Screen.OrderListScreen.route) {
            OrderListScreen()
        }

        composable(Screen.SettingsScreen.route) {
            SettingsScreen()
        }
    }
}