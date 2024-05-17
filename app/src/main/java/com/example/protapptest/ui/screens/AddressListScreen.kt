package com.example.protapptest.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.protapptest.data.remote.payload.response.AddressResponse
import com.example.protapptest.ui.components.AddressItem
import com.example.protapptest.ui.components.MyDialog2
import com.example.protapptest.ui.components.MyDropdownMenu
import com.example.protapptest.ui.events.AddressListEvent
import com.example.protapptest.ui.events.HomeEvent
import com.example.protapptest.ui.navigation.Screen
import com.example.protapptest.ui.navigation.graphs.Graph
import com.example.protapptest.ui.viewmodels.AddressListViewModel
import com.example.protapptest.ui.viewmodels.HomeViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun AddressListScreen(
    homeViewModel: HomeViewModel,
    addressListViewModel: AddressListViewModel = hiltViewModel(),
    navigateTo: (String) -> Unit
) {

    MyDialog2(
        tittle = "¿ Desea eliminar ésta direccion ?  \"${addressListViewModel.currentAddress?.name}\"",
        text = "Esta acción eliminara permanentemente esta dirección",
        show = addressListViewModel.showDialog,
        onDismiss = { addressListViewModel.showDialog = false },
        onConfirm = {
            if (addressListViewModel.id != -1L) {
                addressListViewModel.onEvent(AddressListEvent.DeleteBtnClick(addressListViewModel.id))
                homeViewModel.onEvent(HomeEvent.Refresh)
            }
            addressListViewModel.showDialog = false
        }
    )

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = homeViewModel.state.isRefreshing
    )
    Column(
        modifier = Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            text = "Mis direcciones",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(15.dp))

        HorizontalDivider()
        Spacer(modifier = Modifier.height(15.dp))

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                homeViewModel.onEvent(HomeEvent.Refresh)
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 30.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                homeViewModel.state.producerInfoResponse?.let {
                    items(it.addressList) { addressResponse ->
                        AddressItem(
                            addressResponse = addressResponse,
                            onEditClick = {
                                navigateTo(
                                    Screen.AddEditAddressScreen.route + "?addressId=${addressResponse.id}"
                                )
                            },
                            onDeleteClick = {

                                addressListViewModel.currentAddress = addressResponse

                                Log.d("AddressListScreen", "onDeleteClick !!")
                                addressListViewModel.id = addressResponse.id
                                addressListViewModel.showDialog = true
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        ExtendedFloatingActionButton(
            text = { Text(text = "Agregar dirección") },
            icon = { Icon(Icons.Filled.Add, "Extended floating action button.") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color.LightGray,
            onClick = { navigateTo(Screen.AddEditAddressScreen.route) },
        )
    }
}



