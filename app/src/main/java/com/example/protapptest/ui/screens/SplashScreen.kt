package com.example.protapptest.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.protapptest.ui.events.SplashEvent
import com.example.protapptest.R
import com.example.protapptest.config.common.UserAuthState
import com.example.protapptest.ui.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    userAuthState: UserAuthState? = null
) {
    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.popBackStack()
//        navController.navigate(
//            if (userAuthState == UserAuthState.AUTHENTICATED)
//                Screen.HomeScreen.route
//            else
//                Screen.SignInScreen.route
//        )
    }
    Splash()
}

@Composable
fun Splash() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(imageVector = Icons.Filled.Category, contentDescription = "Logo app")
        Text(text = "Bienvenido")
    }
}


@Composable
@Preview(showBackground = true)
fun SplashScreenPreview() {
    Splash()
}