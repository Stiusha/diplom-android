package com.example.diplom.retrofit.repository

import com.example.diplom.retrofit.dto.CategoryDto
import retrofit2.http.GET

interface CategoryApi {

    @GET("/categories")
    suspend fun getCategories(): List<CategoryDto>
}