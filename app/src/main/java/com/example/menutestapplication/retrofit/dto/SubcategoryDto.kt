package com.example.menutestapplication.retrofit.dto

data class SubcategoryDto(
    val subcategoryId: Long,
    val subcategoryName: String,
    val subcategoryImage: String,
) : Item {
    override fun getId(): Long {
        return subcategoryId
    }

    override fun getName(): String {
        return subcategoryName
    }

    override fun getImage(): String {
        return subcategoryImage
    }
}
