package com.example.protapptest.domain.use_cases.producer

import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.data.local.entities.ProducerEntity
import com.example.protapptest.data.remote.payload.response.ProducerInfoResponse
import com.example.protapptest.domain.repository.ProducerRepository
import kotlinx.coroutines.flow.Flow

class GetLocalProducer(
    private val repository: ProducerRepository
) {
    operator fun invoke(
        id: Long
    ): Flow<ProducerEntity> {
        return repository.getProducer(id)
    }
}