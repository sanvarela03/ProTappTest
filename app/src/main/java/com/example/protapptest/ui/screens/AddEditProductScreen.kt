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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Scale
import androidx.compose.material.icons.outlined.Straighten
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.protapptest.ui.components.ButtonComponent
import com.example.protapptest.ui.components.HeadingTextComponent
import com.example.protapptest.ui.components.MyTextFieldComponentIcons
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.ui.components.MyDialog
import com.example.protapptest.ui.components.MyPicker
import com.example.protapptest.ui.components.MyPicker3
import com.example.protapptest.ui.events.AddEditProductEvent
import com.example.protapptest.ui.events.HomeEvent
import com.example.protapptest.ui.navigation.graphs.Graph
import com.example.protapptest.ui.viewmodels.AddEditProductViewModel

@Composable
fun AddEditProductScreen(
    viewModel: AddEditProductViewModel = hiltViewModel(),
    navigateTo: (String) -> Unit
) {
    val state = viewModel.state
    val api by viewModel.apiFlow.collectAsState()
    val isAttemptingToSave = viewModel.isAttemptingToSave
    val availableList = listOf("Si", "No")

    var msg = ""
    when (api) {
        ApiResponse.Waiting -> {}
        ApiResponse.Loading -> {}
        is ApiResponse.Failure -> {}
        is ApiResponse.Success -> {
            msg = (api as ApiResponse.Success<MessageResponse>).data.message
            viewModel.showDialog = true
        }
    }

    MyDialog(
        tittle = "Producto ${if (isAttemptingToSave) "guardado" else "actualizado"} correctamente",
        text = msg,
        onConfirmTxt = "OK",
        show = viewModel.showDialog,
        onDismiss = { viewModel.showDialog = false },
        onConfirm = {
//            navController.navigate(Screen.HomeDrawerScreen.route)
            viewModel.showDialog = false
//            homeViewModel.onEvent(HomeEvent.Refresh)
            navigateTo(Graph.HOME)
        }
    )


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
            HeadingTextComponent(value = "${if (isAttemptingToSave) "Agregar" else "Editar"} producto")
            Spacer(modifier = Modifier.height(40.dp))
            MyTextFieldComponentIcons(
                labelValue = "Nombre",
                textValue = state.name,
                imageVector = Icons.Outlined.Create,
                onTextSelected = {
                    viewModel.onEvent(AddEditProductEvent.NameChanged(it))
                },
                errorStatus = false
//                signInViewModel.state.usernameError
            )
            MyTextFieldComponentIcons(
                labelValue = "Descripcion",
                textValue = state.description,
                imageVector = Icons.Outlined.Description,
                onTextSelected = {
                    viewModel.onEvent(AddEditProductEvent.DescriptionChanged(it))
                },
                errorStatus = false
//                signInViewModel.state.usernameError
            )


            MyTextFieldComponentIcons(
                labelValue = "Precio",
                textValue = state.price,
                imageVector = Icons.Outlined.AttachMoney,
                onTextSelected = {
                    viewModel.onEvent(AddEditProductEvent.PriceChanged(it))
                },
                errorStatus = false
//                signInViewModel.state.usernameError
            )

            MyTextFieldComponentIcons(
                labelValue = "Peso por unidad en kg",
                textValue = state.weight,
                imageVector = Icons.Outlined.Scale,
                onTextSelected = {
                    viewModel.onEvent(AddEditProductEvent.WeightChanged(it))
                },
                errorStatus = false
//                signInViewModel.state.usernameError
            )

            MyTextFieldComponentIcons(
                labelValue = "Unidades disponibles",
                textValue = state.units,
                imageVector = Icons.Outlined.LocalShipping,
                onTextSelected = {
                    viewModel.onEvent(AddEditProductEvent.UnitsChanged(it))
                },
                errorStatus = false
//                signInViewModel.state.usernameError
            )


            MyTextFieldComponentIcons(
                labelValue = "Largo en cm",
                textValue = state.length,
                imageVector = Icons.Outlined.Straighten,
                onTextSelected = {
                    viewModel.onEvent(AddEditProductEvent.LengthChanged(it))
                },
                errorStatus = false,
//                signInViewModel.state.usernameError
                tint = Color.Red
            )

            MyTextFieldComponentIcons(
                labelValue = "Ancho en cm",
                textValue = state.width,
                imageVector = Icons.Outlined.Straighten,
                onTextSelected = {
                    viewModel.onEvent(AddEditProductEvent.WidthChanged(it))
                },
                errorStatus = false,
//                signInViewModel.state.usernameError
                tint = Color.Green
            )

            MyTextFieldComponentIcons(
                labelValue = "Alto en cm",
                textValue = state.height,
                imageVector = Icons.Outlined.Straighten,
                onTextSelected = {
                    viewModel.onEvent(AddEditProductEvent.HeightChanged(it))
                },
                errorStatus = false,
                tint = Color.Blue
//                signInViewModel.state.usernameError
            )



            MyPicker3(
                textValue = if (state.available) "Si" else "No",
                labelValue = "¿Producto disponible?",
                imageVector = Icons.Outlined.Visibility,
                items = availableList,
                onClick = {
                    viewModel.onEvent(AddEditProductEvent.AvailableChanged(availableList[it] == "Si"))
                },
                errorStatus = false
            )


            Spacer(modifier = Modifier.height(40.dp))
            ButtonComponent(
                value = "Guardar Producto",
                isEnabled = true
            ) {
                Log.d("SignInScreen", "Sign In BTN CLICK!")
                viewModel.onEvent(AddEditProductEvent.SaveBtnClick)

            }
        }
    }
//    Box(
//        modifier = Modifier.fillMaxSize(),
//    ) {
//        LargeFloatingActionButton(
//            containerColor = Color.LightGray,
//            shape = CircleShape,
//            modifier = Modifier
//                .align(Alignment.BottomEnd)
//                .padding(16.dp),
//            onClick = {
//
//            },
//        )
//        { Icon(Icons.Filled.Save, "Extended floating action button.") }
//
//    }

}

