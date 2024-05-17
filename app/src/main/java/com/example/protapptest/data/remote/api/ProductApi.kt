package com.example.protapptest.data.remote.api

import com.example.protapptest.data.remote.payload.request.ProductRequest
import com.example.protapptest.data.remote.payload.request.UpdateProductRequest
import com.example.protapptest.data.remote.payload.request.UserInfoRequest
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.data.remote.payload.response.ProductsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductApi {
    @GET("/v1/api/products")
    suspend fun getProducts(): Response<ProductsResponse>

    @DELETE("/v1/api/products/{productId}")
    suspend fun deleteProduct(@Path("productId") id: Long): Response<MessageResponse>

    @POST("/v1/api/products")
    suspend fun addProduct(@Body productRequest: ProductRequest): Response<MessageResponse>

    @PUT("/v1/api/products/{productId}")
    suspend fun updateProduct(
        @Path("productId") id: Long,
        @Body updateProductRequest: UpdateProductRequest
    ): Response<MessageResponse>

}