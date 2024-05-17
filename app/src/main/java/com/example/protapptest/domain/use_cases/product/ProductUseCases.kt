package com.example.protapptest.domain.use_cases.product

data class ProductUseCases(
    val getProducts: GetProducts,
    val addProduct: AddProduct,
    val getProduct: GetProduct,
    val updateProduct: UpdateProduct,
    val deleteProduct: DeleteProduct
)