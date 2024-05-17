package com.example.protapptest.domain.use_cases.address

data class AddressUseCases(
    val addAddress: AddAddress,
    val updateAddress: UpdateAddress,
    val getAddress: GetAddress,
    val getCurrentAddressId: GetCurrentAddressId,
    val deleteAddress: DeleteAddress
)
