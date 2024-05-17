package com.example.protapptest.domain.use_cases.address

import com.example.protapptest.data.local.entities.AddressEntity
import com.example.protapptest.domain.repository.AddressRepository
import kotlinx.coroutines.flow.Flow

class GetAddress(
    private val repository: AddressRepository
) {
     operator fun invoke(addressId: Long): Flow<AddressEntity> {
        return repository.getAddressById(addressId)
    }

}