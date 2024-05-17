package com.example.protapptest.data.repository

import android.util.Log
import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.config.common.apiRequestFlow
import com.example.protapptest.data.local.dao.ProducerDao
import com.example.protapptest.data.local.entities.ProducerEntity
import com.example.protapptest.data.mapper.toAddressEntity
import com.example.protapptest.data.mapper.toProducerEntity
import com.example.protapptest.data.mapper.toProducerInfoResponse
import com.example.protapptest.data.mapper.toProductEntity
import com.example.protapptest.data.remote.api.ProducerApi
import com.example.protapptest.data.remote.payload.request.UpdateFirebaseTokenRequest
import com.example.protapptest.data.remote.payload.request.UserInfoRequest
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.data.remote.payload.response.ProducerInfoResponse
import com.example.protapptest.domain.repository.ProducerRepository
import com.example.protapptest.security.TokenManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.lastOrNull
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 *
 * **/
@Singleton
class ProducerRepositoryImpl @Inject constructor(
    private val api: ProducerApi,
    private val dao: ProducerDao,
    private val tokenManager: TokenManager
) : ProducerRepository {
    override fun loadProducer(
        fetchFromRemote: Boolean,
        id: Long
    ): Flow<ApiResponse<ProducerInfoResponse>> = flow {

        Log.d("ProducerRepositoryImpl", "getProducer")
        emit(ApiResponse.Loading)
        val localProducerWithAddress = dao.getProducerAndAddress(id)
        val localProducerWithProducts = dao.getProducerAndProduct(id)
        val localProducer = dao.getProducer(id)

        if (localProducer != null && localProducerWithProducts != null) {
            val local = localProducer.toProducerInfoResponse(
                productList = localProducerWithProducts.productList,
                addressList = localProducerWithAddress[0].addressList
            )
            emit(ApiResponse.Success(local))
        }

        val isDbEmpty = localProducer == null
        Log.d(
            "ProducerRepositoryImpl",
            "getProducer | fetchFromRemote = $fetchFromRemote | isDbEmpty = $isDbEmpty "
        )

        val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

        Log.d(
            "ProducerRepositoryImpl",
            "getProducer | shouldJustLoadFromCache = $shouldJustLoadFromCache"
        )

        if (shouldJustLoadFromCache) {
            return@flow
        }

        val remote = getRemote(id)

        remote?.let {
            dao.clearProducerEntity()
            dao.clearAddressEntity()
            dao.clearProductEntity()

            dao.insertProducer(it.toProducerEntity())
            it.addressList.forEach {
                dao.insertAddress(it.toAddressEntity())
            }
            it.productsList.forEach {
                dao.insertProduct(it.toProductEntity())
            }
            val localProducerWithAddress = dao.getProducerAndAddress(it.producerId)[0].addressList
            val localProducerWithProducts = dao.getProducerAndProduct(it.producerId)?.productList
            val localProducer = dao.getProducer(it.producerId)
            Log.d(
                "ProducerRepositoryImpl",
                "getProducer | shouldJustLoadFromCache = $shouldJustLoadFromCache | localProducer = $localProducer"
            )
            if (localProducerWithProducts != null) {


                emit(
                    ApiResponse.Success(
                        data = localProducer!!.toProducerInfoResponse(
                            addressList = localProducerWithAddress,
                            productList = localProducerWithProducts
                        )
                    )
                )
            }
        }


    }

    override fun getProducer(id: Long): Flow<ProducerEntity> {
        return dao.getLocalProducer(id)
    }

    override suspend fun updateAccount(userInfoRequest: UserInfoRequest): Flow<ApiResponse<MessageResponse>> {
        val id = tokenManager.getUserId().first()
        return apiRequestFlow {
            api.updateAccount(id ?: -1L, userInfoRequest)
        }
    }


    override suspend fun updateFirebaseToken(
        request: UpdateFirebaseTokenRequest
    ): Flow<ApiResponse<MessageResponse>> {
        return apiRequestFlow {
            val userId = tokenManager.getUserId().first()

            api.updateFirebaseToken(userId!!, request)
        }
    }

    private suspend fun FlowCollector<ApiResponse<ProducerInfoResponse>>.getRemote(
        id: Long
    ) = try {
        val response = api.getProducer(id)
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
}