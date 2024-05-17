package com.example.protapptest.domain.use_cases.address

import com.example.protapptest.data.local.entities.AddressEntity
import com.example.protapptest.domain.repository.AddressRepository

class GetCurrentAddressId(
    private val repository: AddressRepository
) {

    suspend operator fun invoke(producerId: Long): Long? {
        return repository.getCurrentAddressId(producerId)
    }
}