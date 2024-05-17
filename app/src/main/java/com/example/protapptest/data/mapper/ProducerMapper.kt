package com.example.protapptest.data.mapper

import com.example.protapptest.data.local.entities.AddressEntity
import com.example.protapptest.data.local.entities.ProducerEntity
import com.example.protapptest.data.local.entities.ProductEntity
import com.example.protapptest.data.remote.payload.response.AddressResponse
import com.example.protapptest.data.remote.payload.response.ProducerInfoResponse
import com.example.protapptest.data.remote.payload.response.ProductResponse
import com.example.protapptest.data.remote.payload.response.ProductsResponse

fun ProducerInfoResponse.toProducerEntity(): ProducerEntity {
    return ProducerEntity(
        producerId = producerId,
        username = username,
        name = name,
        lastname = lastname,
        email = email,
        phone = phone,
        currentAddressId = currentAddressId
    )
}


fun AddressResponse.toAddressEntity(): AddressEntity {
    return AddressEntity(
        id = id,
        name = name,
        street = street,
        instruction = instruction,
        latitude = latitude,
        longitude = longitude,
        city = city,
        state = state,
        country = country,
        userId = userId
    )
}

fun ProductResponse.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        description = description,
        weight = weight,
        length = length,
        width = width,
        height = height,
        price = price,
        unitsAvailable = unitsAvailable,
        available = available,
        producerId = producerId
    )
}

fun ProducerEntity.toProducerInfoResponse(
    addressList: List<AddressEntity>,
    productList: List<ProductEntity>
): ProducerInfoResponse {
    return ProducerInfoResponse(
        producerId = producerId,
        username = username,
        name = name,
        lastname = lastname,
        email = email,
        phone = phone,
        currentAddressId = currentAddressId,
        addressList = addressList.map { it.toAddressResponse() },
        productsList = productList.map { it.toProductResponse() }
    )
}

fun AddressEntity.toAddressResponse(): AddressResponse {
    return AddressResponse(
        id = id,
        name = name,
        street = street,
        instruction = instruction,
        latitude = latitude,
        longitude = longitude,
        city = city,
        state = state,
        country = country,
        userId = userId
    )
}

fun ProductEntity.toProductResponse(): ProductResponse {
    return ProductResponse(
        id = id,
        name = name,
        description = description,
        weight = weight,
        length = length,
        width = width,
        height = height,
        price = price,
        unitsAvailable = unitsAvailable,
        available = available,
        producerId = producerId
    )
}