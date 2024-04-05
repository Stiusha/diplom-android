package com.example.diplom.retrofit.dto

data class CategoryDto(
    val categoryId: Long,
    val categoryName: String,
    val categoryImage: String
) : Item {
    override fun getId(): Long {
        return categoryId
    }

    override fun getName(): String {
        return categoryName
    }

    override fun getImage(): String {
        return categoryImage
    }
}
