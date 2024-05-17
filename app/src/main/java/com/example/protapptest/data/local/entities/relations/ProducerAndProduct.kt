package com.example.protapptest.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.protapptest.data.local.entities.AddressEntity
import com.example.protapptest.data.local.entities.ProducerEntity
import com.example.protapptest.data.local.entities.ProductEntity

data class ProducerAndProduct(
    @Embedded val producer: ProducerEntity,
    @Relation(
        parentColumn = "producerId",
        entityColumn = "producerId"
    )
    val productList: List<ProductEntity>
)
