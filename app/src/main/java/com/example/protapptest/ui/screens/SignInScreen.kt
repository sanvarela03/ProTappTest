package com.example.protapptest.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.protapptest.ui.components.NormalTextComponent
import com.example.protapptest.R
import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.ui.components.ButtonComponent
import com.example.protapptest.ui.components.ClickableSignInTextComponent
import com.example.protapptest.ui.components.DividerTextComponent
import com.example.protapptest.ui.components.HeadingTextComponent
import com.example.protapptest.ui.components.MyTextFieldComponent
import com.example.protapptest.ui.components.PasswordTextFieldComponent
import com.example.protapptest.ui.components.UnderLinedTextComponent
import com.example.protapptest.ui.events.SignInEvent
import com.example.protapptest.ui.navigation.Screen
import com.example.protapptest.ui.viewmodels.SignInViewModel
import com.example.protapptest.ui.viewmodels.TokenViewModel

@Composable
fun SignInScreen(
    signInViewModel: SignInViewModel = hiltViewModel(key = "SignInScreen"),
    tokenViewModel: TokenViewModel = hiltViewModel(),
    navController: NavHostController
) {
    Log.d("SignInScreen", "signInViewModel = $signInViewModel")



    SignInScreenFailure(
        signInViewModel = signInViewModel,
        navController = navController,
    )

//    val signInResponse =
//    Log.d(
//        "SignInScreen",
//        "tokenViewModel.isUserSignedIn.value :  ${tokenViewModel.isUserSignedIn.value}"
//    )
//    runBlocking {
//        tokenViewModel.job?.join()
//    }
//
//    if (tokenViewModel.isUserSignedIn.value == true) {
//        ProducerAppRouter.navigateTo(Screen.HomeScreen)
//    }
//
//    val signInResponse = signInViewModel.signInResponse
//    val context = LocalContext.current
//
//    Log.d("SignInScreen", "Entrando a SignInScreen")
//    when (signInResponse.value) {
//        is ApiResponse.Failure -> {
//            val response = signInResponse as ApiResponse.Failure
//
//            SignInScreenFailure(signInViewModel)
//
//            if (response.code != -1) {
//                Toast.makeText(context, "$response", Toast.LENGTH_LONG).show()
//            }
//        }
//
//        is ApiResponse.Loading -> {
//            SignInScreenLoading()
//        }
//
//        is ApiResponse.Success -> {
//            val response = (signInResponse.value as ApiResponse.Success).data
//            Log.d("SignInScreen", "Solicitud exitosa: $response")
//            Toast.makeText(context, "$response", Toast.LENGTH_LONG).show()
//            tokenViewModel.saveAccessToken(response.token)
//            tokenViewModel.saveRefreshToken(response.refreshToken)
//            tokenViewModel.isUserSignedIn.value = true
//            ProducerAppRouter.navigateTo(Screen.HomeScreen)
//        }
//    }

//    SystemBackButtonHandler {
//        ProducerAppRouter.navigateTo(Screen.SignUpScreen)
//    }
}

@Composable
fun SignInScreenLoading() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun SignInScreenFailure(
    signInViewModel: SignInViewModel,
    navController: NavHostController
) {
    val context = LocalContext.current
//    val coroutineScope = rememberCoroutineScope()

    val signInResponse = signInViewModel.signInResponse.value

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ) {
            NormalTextComponent(value = stringResource(id = R.string.sign_in))
            HeadingTextComponent(value = stringResource(id = R.string.welcome))
            Spacer(modifier = Modifier.height(40.dp))

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.username),
                painterResource = painterResource(id = R.drawable.profile),
                onTextSelected = { signInViewModel.onEvent(SignInEvent.UsernameChanged(it)) },
                errorStatus = signInViewModel.state.usernameError
            )

            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource = painterResource(id = R.drawable.ic_lock),
                onTextSelected = { signInViewModel.onEvent(SignInEvent.PasswordChanged(it)) },
                errorStatus = signInViewModel.state.passwordError
            )

            Spacer(modifier = Modifier.height(40.dp))

            UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

            Spacer(modifier = Modifier.height(40.dp))

            ButtonComponent(
                value = stringResource(id = R.string.sign_in),
                isEnabled = signInViewModel.allValidationsPassed
            ) {
                Log.d("SignInScreen", "Sign In BTN CLICK!")
                Log.d("SignInScreen", "signInViewModel = ${signInViewModel.toString()}")
                signInViewModel.onEvent(SignInEvent.LoginButtonClicked(context))
            }

            Spacer(modifier = Modifier.height(20.dp))

            DividerTextComponent()

            ClickableSignInTextComponent(
                tryingToSignIn = false
            ) {
                navController.navigate(Screen.SignUpScreen.route)
//                ProducerAppRouter.navigateTo(Screen.SignUpScreen)
            }
        }
    }
}

//@Composable
//@Preview
//fun SignInScreenPreview() {
//    SignInScreen()
//}