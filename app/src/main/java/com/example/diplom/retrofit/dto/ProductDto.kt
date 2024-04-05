package com.example.diplom.retrofit.dto

data class ProductDto(
    val productId: Long,
    val productName: String,
    val productImage: String,
    val productDescription: String
) : Item {
    override fun getId(): Long {
        return productId
    }

    override fun getName(): String {
        return productName
    }

    override fun getImage(): String {
        return productImage
    }
}
