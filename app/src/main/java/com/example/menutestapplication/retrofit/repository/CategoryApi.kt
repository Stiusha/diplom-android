package com.example.menutestapplication.retrofit.repository

import com.example.menutestapplication.retrofit.dto.CategoryDto
import retrofit2.http.GET

interface CategoryApi {

    @GET("/categories")
    suspend fun getCategories(): List<CategoryDto>
}