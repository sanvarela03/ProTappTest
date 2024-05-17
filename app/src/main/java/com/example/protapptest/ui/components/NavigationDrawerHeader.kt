package com.example.protapptest.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.protapptest.ui.theme.Primary
import com.example.protapptest.ui.theme.Secondary
import com.example.protapptest.R
import com.example.protapptest.ui.theme.AccentColor

@Composable
fun NavigationDrawerHeader(
    value: String?,
    name: String? = "No name"
) {
    Box(
        modifier = Modifier
            .background(Brush.horizontalGradient(listOf(Primary, Secondary)))
            .fillMaxWidth()
            .height(180.dp)
            .padding(32.dp)
    ) {

        Column {
            NavigationDrawerText(
                tittle = value ?: stringResource(id = R.string.navigation_header),
                textUnit = 28.sp,
                color = AccentColor
            )
            if (name != null) {
                Text(text = name, fontSize = 20.sp, fontStyle = FontStyle.Italic)
            }
        }

    }
}