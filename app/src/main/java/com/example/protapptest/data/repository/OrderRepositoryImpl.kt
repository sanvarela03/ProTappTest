package com.example.protapptest.data.repository

import com.example.protapptest.config.common.ApiResponse
import com.example.protapptest.config.common.apiRequestFlow
import com.example.protapptest.data.local.dao.CustomerInfoDao
import com.example.protapptest.data.local.dao.DeliveryAddressDao
import com.example.protapptest.data.local.dao.OrderDao
import com.example.protapptest.data.local.dao.PickupAddressDao
import com.example.protapptest.data.local.dao.ProducerInfoDao
import com.example.protapptest.data.local.dao.StatusDao
import com.example.protapptest.data.local.dao.TransporterInfoDao
import com.example.protapptest.data.local.entities.relations.OrderAndStatus
import com.example.protapptest.data.remote.api.ProducerApi
import com.example.protapptest.data.remote.payload.response.MessageResponse
import com.example.protapptest.data.remote.payload.response.order.OrderInfoResponse
import com.example.protapptest.domain.repository.OrderRepository
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
class OrderRepositoryImpl @Inject constructor(
    private val tokenManager: TokenManager,
    private val producerApi: ProducerApi,
    private val orderDao: OrderDao,
    private val customerInfoDao: CustomerInfoDao,
    private val producerInfoDao: ProducerInfoDao,
    private val pickupAddressDao: PickupAddressDao,
    private val deliveryAddressDao: DeliveryAddressDao,
    private val transporterInfoDao: TransporterInfoDao,
    private val statusDao: StatusDao
) : OrderRepository {
    override suspend fun getAllOrdersWithStatus(fetchFromRemote: Boolean): Flow<ApiResponse<List<OrderAndStatus>>> =
        flow {
            emit(ApiResponse.Loading)
            val customerId = tokenManager.getUserId().first()

            customerId?.let { id ->
                val localOrderAndStatus = orderDao.getOrderAndStatus()

                if (localOrderAndStatus != null) {
                    emit(ApiResponse.Success(localOrderAndStatus))
                }

                val isDbEmpty = localOrderAndStatus == null

                val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

                if (shouldJustLoadFromCache) {
                    return@flow
                }

                val remote = getRemote(id)

                remote?.let { orderInfoResponseList: List<OrderInfoResponse> ->
                    orderDao.clearOrderEntity()
                    customerInfoDao.clearCustomerInfoEntity()
                    producerInfoDao.clearProducerInfoEntity()
                    transporterInfoDao.clearTransporterInfoEntity()
                    pickupAddressDao.clearPickupAddressEntity()
                    deliveryAddressDao.clearDeliveryAddressEntity()
                    statusDao.clearStatusEntity()

                    orderInfoResponseList.forEach {
                        orderDao.insertOrder(it.toOrderEntity())
                        customerInfoDao.insertCustomerInfoEntity(it.toCustomerInfoEntity())
                        producerInfoDao.insertProducerInfoEntity(it.toProducerInfoEntity())
                        if (it.transporterContactInfoResponse!!.transporterId != null) {
                            transporterInfoDao.insertTransporterInfoEntity(it.toTransporterInfoEntity())
                        }
                        pickupAddressDao.insertPickupAddress(it.toPickupAddressEntity())
                        deliveryAddressDao.insertDeliveryAddress(it.toDeliveryAddressEntity())

                        it.statusList.forEach { statusResponse ->
                            statusDao.insertStatus(statusResponse.toStatusEntity())
                        }
                    }
                }

                val newOrderAndStatus = orderDao.getOrderAndStatus()

                newOrderAndStatus?.let {
                    emit(ApiResponse.Success(newOrderAndStatus))
                }
            }
        }

    override suspend fun acceptOrder(orderId: Long): Flow<ApiResponse<MessageResponse>> = flow {
        emit(ApiResponse.Loading)
        val order = orderDao.getOrder(orderId)
        val remoteTest =
            apiRequestFlow(timeOut = 300000L) {
                producerApi.confirmOrder(
                    order.producerId,
                    orderId,
                    true
                )
            }

        remoteTest.collect {
            when (it) {
                ApiResponse.Loading -> {}
                ApiResponse.Waiting -> {}
                is ApiResponse.Failure -> {
                    emit(it)
                }

                is ApiResponse.Success -> {
                    emit(it)
                }
            }
        }

    }

    private suspend fun FlowCollector<ApiResponse<List<OrderAndStatus>>>.getRemote(
        id: Long
    ) = try {
        val response = producerApi.getAllOrders(id)
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