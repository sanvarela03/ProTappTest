package com.example.protapptest.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.protapptest.config.di.module.toCoinFormat
import com.example.protapptest.data.local.entities.relations.OrderAndStatus

@Composable
fun PendingOrderItem(
    order: OrderAndStatus,
    onAccept: (Long) -> Unit,
    onReject: (Long) -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .padding(20.dp)
            .wrapContentHeight()
            .width(400.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(20.dp)
        ) {
            Text(text = "OrderId : ${order.order.orderId}")
            Text(text = " Customer Id: ${order.order.customerId}")
            Text(text = " Order cost: $ ${order.order.orderCost.toInt().toString().toCoinFormat()}")
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Button(
                    onClick = { onAccept(order.order.orderId) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Aceptar")
                }
                Spacer(modifier = Modifier.width(15.dp))
                Button(
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                    onClick = { /*TODO*/ }

                ) {
                    Text(text = "Rechazar")
                }
            }
        }
    }
}