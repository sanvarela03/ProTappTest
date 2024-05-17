package com.example.protapptest.data.remote.payload.response.order


import com.example.protapptest.data.local.entities.CustomerInfoEntity
import com.example.protapptest.data.local.entities.DeliveryAddressEntity
import com.example.protapptest.data.local.entities.OrderEntity
import com.example.protapptest.data.local.entities.PickupAddressEntity
import com.example.protapptest.data.local.entities.ProducerInfoEntity
import com.example.protapptest.data.local.entities.TransporterInfoEntity
import com.example.protapptest.data.remote.payload.response.contact.ProducerContactInfoResponse
import com.example.protapptest.data.remote.payload.response.contact.TransporterContactInfoResponse
import com.example.protapptest.data.remote.payload.response.AddressResponse
import com.example.protapptest.data.remote.payload.response.contact.CustomerContactInfoResponse
import com.example.protapptest.data.remote.payload.response.order.product.ProductItemResponse

data class OrderInfoResponse(
    val orderId: Long,
    val orderCost: Double,
    val orderWeight: Double,
    val orderVolume: Double,
    val estimatedTravelDistance: Double,
    val estimatedTravelDuration: Double,
    val shippingCost: Double,
    val paymentMethod: String,
    val maxDeliveryDate: String,
    val estimatedPickupDate: String,

    val customerContactInfoResponse: CustomerContactInfoResponse,
    val producerContactInfoResponse: ProducerContactInfoResponse,
    val transporterContactInfoResponse: TransporterContactInfoResponse?,
    val pickupAddress: AddressResponse,
    val deliveryAddress: AddressResponse,
    val statusList: List<StatusResponse>,
    val items: List<ProductItemResponse>,
) {
    fun toOrderEntity(): OrderEntity {
        return OrderEntity(
            orderId = orderId,
            orderCost = orderCost,
            orderWeight = orderWeight,
            orderVolume = orderVolume,
            estimatedTravelDistance = estimatedTravelDistance,
            estimatedTravelDuration = estimatedTravelDuration,
            shippingCost = shippingCost,
            paymentMethod = paymentMethod,
            maxDeliveryDate = maxDeliveryDate,
            estimatedPickupDate = estimatedPickupDate,
            transporterId = transporterContactInfoResponse?.transporterId,
            customerId = customerContactInfoResponse.customerId,
            producerId = producerContactInfoResponse.producerId,
        )
    }

    fun toProducerInfoEntity(): ProducerInfoEntity {
        return ProducerInfoEntity(
            id = null,
            producerId = producerContactInfoResponse.producerId,
            completeName = producerContactInfoResponse.completeName,
            phone = producerContactInfoResponse.phone,
            email = producerContactInfoResponse.email,
            orderId = orderId,
        )
    }

    fun toCustomerInfoEntity(): CustomerInfoEntity {
        return CustomerInfoEntity(
            id = null,
            customerId = customerContactInfoResponse.customerId,
            completeName = customerContactInfoResponse.completeName,
            phone = customerContactInfoResponse.phone,
            email = customerContactInfoResponse.email,
            orderId = orderId,
        )
    }

    fun toTransporterInfoEntity(): TransporterInfoEntity {
        return TransporterInfoEntity(
            id = null,
            transporterId = transporterContactInfoResponse?.transporterId,
            completeName = transporterContactInfoResponse?.completeName,
            phone = transporterContactInfoResponse?.phone,
            email = transporterContactInfoResponse?.email,
            orderId = orderId,
        )
    }

    fun toDeliveryAddressEntity(): DeliveryAddressEntity {
        return DeliveryAddressEntity(
            id = null,
            addressId = deliveryAddress.id,
            name = deliveryAddress.name,
            street = deliveryAddress.street,
            instruction = deliveryAddress.instruction,
            latitude = deliveryAddress.latitude,
            longitude = deliveryAddress.longitude,
            city = deliveryAddress.city,
            state = deliveryAddress.state,
            country = deliveryAddress.country,
            userId = deliveryAddress.userId,
            orderId = orderId,
        )
    }

    fun toPickupAddressEntity(): PickupAddressEntity {
        return PickupAddressEntity(
            id = null,
            addressId = pickupAddress.id,
            name = pickupAddress.name,
            street = pickupAddress.street,
            instruction = pickupAddress.instruction,
            latitude = pickupAddress.latitude,
            longitude = pickupAddress.longitude,
            city = pickupAddress.city,
            state = pickupAddress.state,
            country = pickupAddress.country,
            userId = pickupAddress.userId,
            orderId = orderId,
        )
    }

}
