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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.protapptest.R
import com.example.protapptest.ui.components.ButtonComponent
import com.example.protapptest.ui.components.ButtonWithIcon
import com.example.protapptest.ui.components.CheckboxComponent
import com.example.protapptest.ui.components.ClickableSignInTextComponent
import com.example.protapptest.ui.components.DividerTextComponent
import com.example.protapptest.ui.components.HeadingTextComponent
import com.example.protapptest.ui.components.MyDialog
import com.example.protapptest.ui.components.MyNumberFieldComponent
import com.example.protapptest.ui.components.MyTextFieldComponentIcons
import com.example.protapptest.ui.components.NormalTextComponent
import com.example.protapptest.ui.components.PasswordTextFieldComponentIcons
import com.example.protapptest.ui.events.SettingsEvent
import com.example.protapptest.ui.events.SignUpEvent
import com.example.protapptest.ui.navigation.Screen
import com.example.protapptest.ui.viewmodels.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {

    var state = viewModel.state

    val scrollState = rememberScrollState()

    MyDialog(
        tittle = "Actualizacion de cuenta",
        text = viewModel.msgResponse,
        show = viewModel.showDialog,
        onDismiss = { /*TODO*/ }) {
        viewModel.showDialog = false
    }


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
            .verticalScroll(scrollState),

        ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {

            // NormalTextComponent(value = stringResource(id = R.string.hello))
            HeadingTextComponent(value = "Configuración de cuenta")
            Spacer(modifier = Modifier.height(50.dp))


            MyTextFieldComponentIcons(
                labelValue = stringResource(id = R.string.username),
                textValue = state.username,
                imageVector = Icons.Outlined.AccountCircle,
                onTextSelected = {
                    viewModel.onEvent(SettingsEvent.UsernameChanged(it))
                },
                errorStatus =
                viewModel.state.lastNameError
            )

            MyTextFieldComponentIcons(
                labelValue = stringResource(id = R.string.first_name),
                textValue = state.firstName,
                imageVector = Icons.Outlined.Person,
                onTextSelected = {
                    viewModel.onEvent(SettingsEvent.FirstNameChanged(it))
                },
                errorStatus =
                viewModel.state.firstNameError
            )
            MyTextFieldComponentIcons(
                labelValue = stringResource(id = R.string.last_name),
                textValue = state.lastName,
                imageVector = Icons.Outlined.Person,
                onTextSelected = {
                    viewModel.onEvent(SettingsEvent.LastNameChanged(it))
                },
                errorStatus =
                viewModel.state.lastNameError
            )

            MyTextFieldComponentIcons(
                labelValue = stringResource(id = R.string.email),
                textValue = state.email,
                imageVector = Icons.Outlined.Mail,
                onTextSelected = {
                    viewModel.onEvent(SettingsEvent.EmailChanged(it))
                },
                errorStatus =
                viewModel.state.emailError
            )

            MyNumberFieldComponent(
                labelValue = stringResource(id = R.string.phone),
                textValue = state.phone,
                imageVector = Icons.Outlined.PhoneAndroid,
                onTextSelected = {
                    viewModel.onEvent(SettingsEvent.PhoneChanged(it))
                },
                errorStatus = viewModel.state.phoneError
            )

//                PasswordTextFieldComponentIcons(
//                    labelValue = stringResource(id = R.string.password),
//                    imageVector = Icons.Outlined.Lock,
//                    onTextSelected = {
//                        viewModel.onEvent(SettingsEvent.PasswordChanged(it))
//                    },
//                    errorStatus = viewModel.state.passwordError
//                )

            Spacer(modifier = Modifier.height(50.dp))

            ButtonComponent(
                value = "Actualizar cuenta",
                isEnabled = viewModel.allValidationsPassed
            ) {
                viewModel.onEvent(SettingsEvent.UpdateButtonClicked)
            }


            Spacer(modifier = Modifier.height(25.dp))


            ButtonWithIcon(
                value = "Eliminar cuenta",
                isEnabled = viewModel.allValidationsPassed,
                imageVector = Icons.Filled.Delete
            ) {
                viewModel.onEvent(SettingsEvent.DeleteButtonClicked)
            }
        }
    }

}

@Composable
@Preview(showSystemUi = true)
fun SettingsScreenPreview() {
    SettingsScreen()
}