package com.example.protapptest.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.protapptest.data.local.dto.NavigationItem

@Composable
fun NavigationDrawerBody(
    navigationItems: List<NavigationItem>,
    navigateTo: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(navigationItems) { navItem: NavigationItem ->
            NavigationItemRow(item = navItem, navigateTo = { navigateTo(navItem.itemId) })
        }
    }
}