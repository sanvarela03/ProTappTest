package com.example.protapptest.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.protapptest.data.local.entities.AddressEntity
import com.example.protapptest.data.local.entities.ProducerEntity

data class ProducerAndAddress(
    @Embedded val producer: ProducerEntity,
    @Relation(
        parentColumn = "producerId",
        entityColumn = "userId"
    )
    val addressList: List<AddressEntity>
)
