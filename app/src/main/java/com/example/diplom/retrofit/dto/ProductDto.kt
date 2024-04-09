package com.example.diplom.retrofit.dto

data class ProductDto(
    val productId: Long,
    val productName: String,
    val productPrice: Long,
    val productRate: Long,
    val productImage: String,
    val productDescription: String
)