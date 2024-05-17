package com.example.protapptest.domain.use_cases.producer

import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.remote.payload.response.ProducerInfoResponse
import com.example.protapptest.domain.repository.ProducerRepository
import kotlinx.coroutines.flow.Flow

class GetProducer(
    private val repository: ProducerRepository
) {
    operator fun invoke(
        fetchFromRemote: Boolean,
        id: Long
    ): Flow<ApiResponse<ProducerInfoResponse>> {
        return repository.loadProducer(fetchFromRemote, id)
    }
}