package com.example.protapptest.ui.states

import com.example.protapptest.data.remote.payload.response.ProducerInfoResponse

data class HomeState(
    var producerInfoResponse: ProducerInfoResponse? = null,
    val isRefreshing: Boolean = false,
)
