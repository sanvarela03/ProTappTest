package com.example.protapptest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.protapptest.data.local.entities.CustomerInfoEntity
import com.example.protapptest.data.local.entities.ProducerInfoEntity

@Dao
interface ProducerInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducerInfoEntity(producerInfoEntity: ProducerInfoEntity)

    @Transaction
    @Query("SELECT * FROM ProducerInfoEntity WHERE producerId = :producerId")
    suspend fun getProducerInfoEntity(producerId: Long): ProducerInfoEntity

    @Transaction
    @Query("DELETE FROM ProducerInfoEntity")
    suspend fun clearProducerInfoEntity()
}