package com.example.protapptest.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.protapptest.ui.components.OrderItem
import com.example.protapptest.ui.events.OrderListEvent
import com.example.protapptest.ui.viewmodels.OrderListViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OrderListScreen(
    orderListViewModel: OrderListViewModel = hiltViewModel()
) {
    val state = orderListViewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = orderListViewModel.state.isRefreshing
    )

    Column(
        modifier = Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mis pedidos",
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
                orderListViewModel.onEvent(OrderListEvent.Refresh)
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 30.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (state.orderList.isNotEmpty()) {
                    items(state.orderList) { order ->
                        OrderItem(order)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

