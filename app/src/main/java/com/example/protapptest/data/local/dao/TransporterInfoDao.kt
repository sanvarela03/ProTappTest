package com.example.protapptest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.protapptest.data.local.entities.ProducerInfoEntity
import com.example.protapptest.data.local.entities.TransporterInfoEntity

@Dao
interface TransporterInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransporterInfoEntity(transporterInfoEntity: TransporterInfoEntity)

    @Transaction
    @Query("SELECT * FROM TransporterInfoEntity WHERE transporterId = :transporterId")
    suspend fun getTransporterInfoEntity(transporterId: Long): TransporterInfoEntity

    @Transaction
    @Query("DELETE FROM TransporterInfoEntity")
    suspend fun clearTransporterInfoEntity()
}