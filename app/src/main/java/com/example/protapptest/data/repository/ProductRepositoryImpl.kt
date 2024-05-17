package com.example.protapptest.data.repository

import android.util.Log
import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.config.common.apiRequestFlow
import com.example.protapptest.data.local.dao.ProducerDao
import com.example.protapptest.data.local.entities.ProductEntity
import com.example.protapptest.data.remote.api.ProductApi
import com.example.protapptest.data.remote.payload.request.ProductRequest
import com.example.protapptest.data.remote.payload.request.UpdateProductRequest
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.domain.repository.ProductRepository
import com.example.protapptest.security.TokenManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApi,
    private val dao: ProducerDao,
    private val tokenManager: TokenManager
) : ProductRepository {
    override suspend fun getProducts(fetchFromRemote: Boolean): Flow<ApiResponse<List<ProductEntity>>> {
        return flow {
            emit(ApiResponse.Loading)
            val userId = tokenManager.getUserId().first()
            if (userId != null) {
                val localProducerAndProduct = dao.getProducerAndProduct(userId)
                if (localProducerAndProduct != null) {
                    val localProducts = localProducerAndProduct.productList
                    emit(ApiResponse.Success(localProducts))
                }


                val isDbEmpty = localProducerAndProduct != null

                val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

                if (shouldJustLoadFromCache) {
                    return@flow
                }

                val remote = getFromRemote()

                remote?.let { productsResponse ->
                    dao.clearProductEntity()
                    val remoteProducts = productsResponse.products

                    remoteProducts.forEach {
                        dao.insertProduct(it.toProductEntity())
                    }

                    val prod1 = dao.getProducerAndProduct(userId)

                    if (prod1 != null) {
                        val localProducts1 = prod1.productList
                        emit(ApiResponse.Success(localProducts1))
                    }

                }
            }
        }
    }

    private suspend fun FlowCollector<ApiResponse<List<ProductEntity>>>.getFromRemote() =
        try {
            val response = api.getProducts()
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: IOException) {
            e.printStackTrace()
            emit(ApiResponse.Failure("No se pudo cargar los datos", 400))
            null
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(ApiResponse.Failure("No se pudo cargar los datos", 400))
            null
        }


    override suspend fun addNewProduct(productRequest: ProductRequest): Flow<ApiResponse<MessageResponse>> {
        return apiRequestFlow {
            api.addProduct(productRequest)
        }
    }

    override suspend fun getProduct(id: Long): ProductEntity? {
        val product = dao.getProduct(id)
        if (product != null) {
            return product
        } else {
            Log.d("ProductRepositoryImpl", "getProduct | product = $product")
            return null
        }

    }

    override suspend fun deleteProduct(id: Long): Flow<ApiResponse<MessageResponse>> {
        return apiRequestFlow {
            dao.deleteProduct(id)
            api.deleteProduct(id)
        }
    }

    override suspend fun updateProduct(
        productId: Long,
        updateProductRequest: UpdateProductRequest
    ): Flow<ApiResponse<MessageResponse>> {
        return apiRequestFlow {
            api.updateProduct(productId, updateProductRequest)
        }
    }
}