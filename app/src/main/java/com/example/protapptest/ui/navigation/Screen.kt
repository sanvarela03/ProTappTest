package com.example.protapptest.ui.navigation

sealed class Screen(val route: String) {
    object SignUpScreen : Screen("sign_up_screen")
    object TermsAndConditionsScreen : Screen("terms_and_conditions_screen")
    object SignInScreen : Screen("sign_in_screen")
    object HomeScreen : Screen("home_screen")
    object AddEditProductScreen : Screen("add_edit_product_screen")
    object ProductsScreen : Screen("products_screen")
    object HomeDrawerScreen : Screen("home_drawer_screen")
    object SplashScreen : Screen("splash_screen")
    object AddressListScreen : Screen("address_list_screen")
    object AddEditAddressScreen : Screen("add_edit_address_screen")
    object NotificationsScreen : Screen("notifications_screen")
    object RootScreen : Screen("root_screen")
    object PendingOrdersScreen : Screen("pending_orders_screen")
    object OrderListScreen : Screen("order_list_screen")
    object SettingsScreen : Screen("settings_screen")
}

//object ProducerAppRouter {
//    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.SignInScreen)
//
//    fun navigateTo(destination: Screen) {
//        currentScreen.value = destination
//    }
//}