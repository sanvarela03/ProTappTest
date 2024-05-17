package com.example.protapptest.data.remote.api

import com.example.protapptest.data.remote.payload.request.UpdateFirebaseTokenRequest
import com.example.protapptest.data.remote.payload.request.UserInfoRequest
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.data.remote.payload.response.ProducerInfoResponse
import com.example.protapptest.data.remote.payload.response.order.OrderInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ProducerApi {
    @GET("/v1/api/producers/{userId}")
    suspend fun getProducer(@Path("userId") id: Long): Response<ProducerInfoResponse>

    @GET("v1/api/users/{userId}/orders")
    suspend fun getAllOrders(@Path("userId") id: Long): Response<List<OrderInfoResponse>>

    @PUT("/v1/api/producers/{userId}/orders/{orderId}")
    suspend fun confirmOrder(
        @Path("userId") userId: Long,
        @Path("orderId") orderId: Long,
        @Query("accepted") accepted: Boolean
    ): Response<MessageResponse>

    @PUT("/v1/api/users/{userId}/firebase-token")
    suspend fun updateFirebaseToken(
        @Path("userId") userId: Long,
        @Body request: UpdateFirebaseTokenRequest
    ): Response<MessageResponse>

    @PUT("/v1/api/users/{userId}")
    suspend fun updateAccount(
        @Path("userId") userId: Long,
        @Body userInfo: UserInfoRequest
    ): Response<MessageResponse>
}