package com.example.protapptest.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.protapptest.data.local.entities.OrderEntity
import com.example.protapptest.data.local.entities.StatusEntity
import com.example.protapptest.data.remote.payload.response.AddressResponse
import com.example.protapptest.data.remote.payload.response.contact.CustomerContactInfoResponse
import com.example.protapptest.data.remote.payload.response.contact.ProducerContactInfoResponse
import com.example.protapptest.data.remote.payload.response.contact.TransporterContactInfoResponse
import com.example.protapptest.data.remote.payload.response.order.OrderInfoResponse
import com.example.protapptest.data.remote.payload.response.order.product.ProductItemResponse

data class OrderAndStatus(
    @Embedded val order: OrderEntity,
    @Relation(
        parentColumn = "orderId",
        entityColumn = "orderId"
    )
    val statusList: List<StatusEntity>
) {
    fun toOrderInfoResponse(
        customerContactInfoResponse: CustomerContactInfoResponse,
        producerContactInfoResponse: ProducerContactInfoResponse,
        transporterContactInfoResponse: TransporterContactInfoResponse?,
        pickupAddress: AddressResponse,
        deliveryAddress: AddressResponse,
        items: List<ProductItemResponse> = emptyList()
    ): OrderInfoResponse {
        return OrderInfoResponse(
            orderId = order.orderId,
            orderCost = order.orderCost,
            orderWeight = order.orderWeight,
            orderVolume = order.orderVolume,
            estimatedTravelDistance = order.estimatedTravelDistance,
            estimatedTravelDuration = order.estimatedTravelDuration,
            shippingCost = order.shippingCost,
            paymentMethod = order.paymentMethod,
            maxDeliveryDate = order.maxDeliveryDate,
            estimatedPickupDate = if (order.estimatedPickupDate != null) order.estimatedPickupDate else "",
            customerContactInfoResponse = customerContactInfoResponse,
            producerContactInfoResponse = producerContactInfoResponse,
            transporterContactInfoResponse = transporterContactInfoResponse,
            pickupAddress = pickupAddress,
            deliveryAddress = deliveryAddress,
            statusList = statusList.map { it.toStatusResponse() },
            items = items,
            )
    }
}
