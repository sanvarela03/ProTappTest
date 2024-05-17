package com.example.protapptest.domain.use_cases.producer

data class ProducerUseCases(
    val getProducer: GetProducer,
    val getLocalProducer: GetLocalProducer,
    val updateFirebaseToken : UpdateFirebaseToken,
    val updateAccount : UpdateAccount
)
