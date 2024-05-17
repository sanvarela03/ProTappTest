package com.example.protapptest.ui.states

import com.example.protapptest.data.local.entities.ProductEntity
import com.example.protapptest.data.remote.payload.response.ProductResponse
import com.example.protapptest.data.remote.payload.response.ProductsResponse

data class ProductsState(
    val products: List<ProductEntity> = emptyList(),
    val isRefreshing: Boolean = false,
    var msgResponse: String = "",
    var productIdDeleted: Long = -1L
)