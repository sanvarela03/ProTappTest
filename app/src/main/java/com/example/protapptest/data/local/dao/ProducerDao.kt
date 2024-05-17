package com.example.protapptest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.protapptest.data.local.entities.AddressEntity
import com.example.protapptest.data.local.entities.ProducerEntity
import com.example.protapptest.data.local.entities.ProductEntity
import com.example.protapptest.data.local.entities.relations.ProducerAndAddress
import com.example.protapptest.data.local.entities.relations.ProducerAndProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface ProducerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducer(producerEntity: ProducerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddress(addressEntity: AddressEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(productEntity: ProductEntity)

    @Transaction
    @Query("SELECT * FROM ProducerEntity WHERE producerId = :producerId")
    suspend fun getProducerAndAddress(producerId: Long): List<ProducerAndAddress>

    @Transaction
    @Query("SELECT * FROM ProducerEntity WHERE producerId = :producerId")
    suspend fun getProducerAndProduct(producerId: Long): ProducerAndProduct?

    @Transaction
    @Query("SELECT * FROM ProducerEntity WHERE producerId = :producerId")
    suspend fun getProducer(producerId: Long): ProducerEntity?

    @Transaction
    @Query("SELECT * FROM ProducerEntity WHERE producerId = :producerId")
    fun getLocalProducer(producerId: Long): Flow<ProducerEntity>

    @Transaction
    @Query("SELECT * FROM ProducerEntity WHERE producerId = :producerId")
    suspend fun getLocal(producerId: Long): ProducerEntity


    @Transaction
    @Query("SELECT * FROM ProductEntity WHERE id = :productId")
    suspend fun getProduct(productId: Long): ProductEntity?

    @Transaction
    @Query("SELECT ProducerEntity.currentAddressId FROM ProducerEntity WHERE producerId = :producerId")
    suspend fun getCurrentAddressId(producerId: Long): Long?

    @Transaction
    @Query("SELECT * FROM AddressEntity WHERE  id = :addressId")
    fun getAddress(addressId: Long): Flow<AddressEntity>

    @Query(
        """
            SELECT * 
            FROM ProductEntity
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
                UPPER(:query) == description
        """
    )
    suspend fun searchProductListing(query: String): List<ProductEntity>

    @Transaction
    @Query("DELETE FROM ProducerEntity")
    suspend fun clearProducerEntity()

    @Transaction
    @Query("DELETE FROM ProductEntity WHERE id = :id")
    suspend fun deleteProduct(id: Long)

    @Transaction
    @Query("DELETE FROM AddressEntity")
    suspend fun clearAddressEntity()

    @Transaction
    @Query("DELETE FROM ProductEntity")
    suspend fun clearProductEntity()
}