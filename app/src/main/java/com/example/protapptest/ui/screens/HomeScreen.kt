package com.example.protapptest.ui.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.protapptest.ui.components.AppToolBar
import com.example.protapptest.ui.components.NavigationDrawerBody
import com.example.protapptest.ui.components.NavigationDrawerHeader
import com.example.protapptest.ui.viewmodels.HomeViewModel
import com.example.protapptest.R
import com.example.protapptest.ui.components.HomeBottomBar
import com.example.protapptest.ui.events.HomeEvent
import com.example.protapptest.ui.navigation.HomeNavigation
import com.example.protapptest.ui.navigation.Screen
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen() {
    HomeDrawer(
        homeViewModel = hiltViewModel(),
        homeNavController = rememberNavController()
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun HomeDrawer(
    homeViewModel: HomeViewModel,
    homeNavController: NavHostController
) {
    val screens = listOf(
        Screen.AddressListScreen.route,
        Screen.ProductsScreen.route,
        Screen.PendingOrdersScreen.route,
        Screen.NotificationsScreen.route,
    )
    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val showTopBar = screens.any { it == currentDestination?.route }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val state = homeViewModel.state

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            if (showTopBar) {
                ModalDrawerSheet {
                    NavigationDrawerHeader(
                        value = state.producerInfoResponse?.name + " " + state.producerInfoResponse?.lastname,
                        name = state.producerInfoResponse?.email
                    )
                    NavigationDrawerBody(
                        homeViewModel.navigationItemsList,
                        navigateTo = {
                            scope.launch { drawerState.apply { if (isClosed) open() else close() } }
                            homeNavController.navigate(it) {
                                popUpTo(it) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                if (showTopBar) {
                    AppToolBar(
                        toolbarTitle = stringResource(id = R.string.home),
                        signOutButtonClicked = {
                            homeViewModel.onEvent(HomeEvent.SignOutBtnClicked)
                        },
                        navButtonClicked = {
                            scope.launch { drawerState.apply { if (isClosed) open() else close() } }
                        }
                    )
                }
            },
            bottomBar = {
                HomeBottomBar(
                    navigationItems = homeViewModel.bottomNavigationItemsList,
                    currentDestination = homeNavController.currentBackStackEntryAsState().value?.destination,
                    navigateTo = {
                        homeNavController.navigate(it) {
                            popUpTo(it) {
                                inclusive = true
                            }
                        }
//                        {
//                            popUpTo(homeNavController.graph.findStartDestination().id) {
//                                saveState = true
//                            }
//                            restoreState = true
//                            launchSingleTop = true
//                        }
                    }
                )
            }
        ) { contentPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
//                    .background(Color.White)
                    .padding(contentPadding)
            ) {
                HomeNavigation(
//                    homeViewModel = homeViewModel,
                    homeNavController = homeNavController,
                )
            }

        }
    }
}