package com.example.protapptest.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material.icons.outlined.PhoneIphone
import androidx.compose.material.icons.rounded.PhoneIphone
import androidx.compose.material.icons.sharp.PhoneAndroid
import androidx.compose.material.icons.sharp.PhoneIphone
import androidx.compose.material.icons.twotone.PhoneAndroid
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.protapptest.ui.components.NormalTextComponent
import com.example.protapptest.R
import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.ui.components.ButtonComponent
import com.example.protapptest.ui.components.CheckboxComponent
import com.example.protapptest.ui.components.ClickableSignInTextComponent
import com.example.protapptest.ui.components.DividerTextComponent
import com.example.protapptest.ui.components.HeadingTextComponent
import com.example.protapptest.ui.components.MyDialog
import com.example.protapptest.ui.components.MyNumberFieldComponent
import com.example.protapptest.ui.components.MyTextFieldComponent
import com.example.protapptest.ui.components.MyTextFieldComponentIcons
import com.example.protapptest.ui.components.PasswordTextFieldComponent
import com.example.protapptest.ui.components.PasswordTextFieldComponentIcons
import com.example.protapptest.ui.events.SignUpEvent
import com.example.protapptest.ui.navigation.Screen
import com.example.protapptest.ui.viewmodels.SignUpViewModel

@Composable
fun SignUpScreen(
    navController: NavHostController,
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {
    val state = signUpViewModel.state
    val scrollState = rememberScrollState()
    val signUpResponse by signUpViewModel.signUpResponse.collectAsState()
    var msg = ""
    when (signUpResponse) {
        ApiResponse.Waiting -> {}
        ApiResponse.Loading -> {}
        is ApiResponse.Failure -> {}
        is ApiResponse.Success -> {
            msg = (signUpResponse as ApiResponse.Success<MessageResponse>).data.message
        }
    }

    MyDialog(
        tittle = "Registrado exitosamente",
        text = msg,
        show = state.showDialog,
        onDismiss = { state.showDialog = false },
        onConfirm = {
            navController.navigate(Screen.SignInScreen.route)
        }
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
            .verticalScroll(scrollState),

        ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {

            NormalTextComponent(value = stringResource(id = R.string.hello))
            HeadingTextComponent(value = stringResource(id = R.string.create_account))
            Spacer(modifier = Modifier.height(50.dp))


            MyTextFieldComponentIcons(
                labelValue = stringResource(id = R.string.username),
                textValue = state.username,
                imageVector = Icons.Outlined.AccountCircle,
                onTextSelected = {
                    signUpViewModel.onEvent(SignUpEvent.UsernameChanged(it))
                },
                errorStatus =
                signUpViewModel.state.lastNameError
            )

            MyTextFieldComponentIcons(
                labelValue = stringResource(id = R.string.first_name),
                textValue = state.firstName,
                imageVector = Icons.Outlined.Person,
                onTextSelected = {
                    signUpViewModel.onEvent(SignUpEvent.FirstNameChanged(it))
                },
                errorStatus =
                signUpViewModel.state.firstNameError
            )
            MyTextFieldComponentIcons(
                labelValue = stringResource(id = R.string.last_name),
                textValue = state.lastName,
                imageVector = Icons.Outlined.Person,
                onTextSelected = {
                    signUpViewModel.onEvent(SignUpEvent.LastNameChanged(it))
                },
                errorStatus =
                signUpViewModel.state.lastNameError
            )

            MyTextFieldComponentIcons(
                labelValue = stringResource(id = R.string.email),
                textValue = state.email,
                imageVector = Icons.Outlined.Mail,
                onTextSelected = {
                    signUpViewModel.onEvent(SignUpEvent.EmailChanged(it))
                },
                errorStatus =
                signUpViewModel.state.emailError
            )

            MyNumberFieldComponent(
                labelValue = stringResource(id = R.string.phone),
                textValue = state.phone,
                imageVector = Icons.Outlined.PhoneAndroid,
                onTextSelected = {
                    signUpViewModel.onEvent(SignUpEvent.PhoneChanged(it))
                },
                errorStatus = signUpViewModel.state.phoneError
            )

            PasswordTextFieldComponentIcons(
                labelValue = stringResource(id = R.string.password),
                imageVector = Icons.Outlined.Lock,
                onTextSelected = {
                    signUpViewModel.onEvent(SignUpEvent.PasswordChanged(it))
                },
                errorStatus = signUpViewModel.state.passwordError
            )
            CheckboxComponent(
                value = stringResource(id = R.string.terms_and_conditions),
                onTextSelected = {
                    Log.d("IT WTF: ", "-> $it")
                    navController.navigate(Screen.TermsAndConditionsScreen.route)
//                ProducerAppRouter.navigateTo(Screen.TermsAndConditionsScreen)
                },
                onCheckedChange = {
                    signUpViewModel.onEvent(SignUpEvent.PrivacyPolicyCheckBoxClicked(it))
                }
            )

            Spacer(modifier = Modifier.height(50.dp))

            ButtonComponent(
                value = stringResource(id = R.string.register),
                isEnabled = signUpViewModel.allValidationsPassed
            ) {
                signUpViewModel.onEvent(SignUpEvent.RegisterButtonClicked)
            }

            DividerTextComponent()

            ClickableSignInTextComponent(tryingToSignIn = true) {
//                ProducerAppRouter.navigateTo(Screen.SignInScreen)
                navController.navigate(Screen.SignInScreen.route)
            }
        }
    }
}