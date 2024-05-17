package com.example.protapptest.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.protapptest.config.di.module.toCoinFormat
import com.example.protapptest.data.local.entities.relations.OrderAndStatus
import com.example.protapptest.ui.components.MyDialog
import com.example.protapptest.ui.components.PendingOrderItem
import com.example.protapptest.ui.events.OrderListEvent
import com.example.protapptest.ui.events.PendingOrderEvent
import com.example.protapptest.ui.viewmodels.PendingOrderViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun PendingOrderScreen(
    pendingOrderViewModel: PendingOrderViewModel = hiltViewModel()
) {
    val state = pendingOrderViewModel.state

    MyDialog(
        tittle = "Confirmacion exitosa",
        text = state.msgResponse,
        show = pendingOrderViewModel.showDialog,
        onDismiss = { /*TODO*/ }) {
        pendingOrderViewModel.showDialog = false
    }


    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = state.isRefreshing
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                pendingOrderViewModel.onEvent(PendingOrderEvent.Refresh)
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 30.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(state.orderList) { order ->
                    PendingOrderItem(
                        order,
                        onAccept = { orderId ->
                            pendingOrderViewModel.onEvent(PendingOrderEvent.AcceptBtnClick(orderId))
                        },
                        onReject = {}
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PendingOrderScreenPreview() {
    PendingOrderScreen()
}

