package com.example.protapptest.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.protapptest.R
import com.example.protapptest.ui.components.MyDialog
import com.example.protapptest.ui.components.MyDialog2
import com.example.protapptest.ui.components.ProductItem
import com.example.protapptest.ui.events.ProductsEvent
import com.example.protapptest.ui.navigation.Screen
import com.example.protapptest.ui.viewmodels.ProductsViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel,
    navigateTo: (String) -> Unit
) {
    val state = viewModel.state

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )

    MyDialog2(
        tittle = stringResource(id = R.string.delete_product),
        text = stringResource(id = R.string.confirm_delete_product),
        show = viewModel.showDeleteDialog,
        onDismiss = { viewModel.showDeleteDialog = false }) {
        if (state.productIdDeleted != -1L) {
            viewModel.onEvent(
                ProductsEvent.DeleteBtnClick(state.productIdDeleted)
            )
        }
        viewModel.showDeleteDialog = false
    }

    MyDialog(
        tittle = stringResource(id = R.string.delete_response),
        text = state.msgResponse,
        show = viewModel.showResponseDialog,
        onDismiss = { viewModel.showResponseDialog = false }) {
        viewModel.showResponseDialog = false
    }


    Column(
        modifier = Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mis productos",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(15.dp))

        HorizontalDivider()

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.onEvent(ProductsEvent.Refresh)
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 30.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(state.products) { product ->

                    ProductItem(
                        product = product,
                        onEditClick = {
                            navigateTo(Screen.AddEditProductScreen.route + "?productId=${product.id}")
                        },
                        onDeleteClick = {
                            viewModel.showDeleteDialog = true
                            state.productIdDeleted = product.id
                        }

                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        ExtendedFloatingActionButton(
            containerColor = Color.LightGray,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = {
                navigateTo(Screen.AddEditProductScreen.route)
            },
            icon = { Icon(Icons.Filled.Add, "Extended floating action button.") },
            text = { Text(text = "Agregar producto") },
        )
    }
}