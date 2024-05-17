package com.example.protapptest.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.protapptest.data.local.entities.ProductEntity
import com.example.protapptest.ui.theme.MyGreen
import com.example.protapptest.ui.theme.Primary
import com.example.protapptest.ui.theme.Tomato

@Composable
fun ProductItem(
    product: ProductEntity,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
) {
    OutlinedCard(
//    ElevatedCard(
//        colors = CardDefaults.cardColors(
//            containerColor = Color.LightGray,
//        ),
//        border = BorderStroke(0.5.dp, Color.Black),
        modifier = Modifier
            .wrapContentHeight()
            .width(400.dp)
//            .size(
//                width = 400.dp,
//                height = 150.dp
//            )

    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = product.name.replaceFirstChar { it.uppercase() },
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
                MyDropdownMenu(
                    onDeleteClick = { onDeleteClick() },
                    onEditClick = { onEditClick() }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = product.description,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "$ ${
                    product.price
                        .toInt()
                        .toString()
                        .replace(Regex("(\\d)(?=(\\d{3})+(?!\\d))"), "$1 ")
                }",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row {

                Text(
                    modifier = Modifier.weight(6f),
                    text = "${if (product.available) "Disponible" else "No disponible"}",
                    fontWeight = FontWeight.Bold,
                    color = if (product.available) {
                        MyGreen
                    } else {
                        Tomato
                    },
                    fontSize = 20.sp
                )

                Icon(
                    modifier = Modifier.weight(1f),
                    imageVector = if (product.available) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = ""
                )
            }


        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun ProductItemPreview() {
    ProductItem(
        product = ProductEntity(
            id = 1,
            name = "String",
            description = "String",
            weight = 0.0,
            length = 0.0,
            width = 0.0,
            height = 0.0,
            price = 0.0,
            unitsAvailable = 1,
            available = true,
            producerId = 1
        ),
        onEditClick = {},
        onDeleteClick = {}
    )
}