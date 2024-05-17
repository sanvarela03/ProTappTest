package com.example.protapptest.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun MyDialog2(
    tittle: String,
    text: String,
    show: Boolean,
    onDismissTxt: String = "Rechazar",
    onConfirmTxt: String = "Confirmar",
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (show) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = onDismissTxt)
                }
            },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = onConfirmTxt)
                }
            },
            title = { Text(text = tittle) },
            text = { Text(text = text) }
        )
    }
}