package com.example.diplom.retrofit.repository

import com.example.diplom.retrofit.dto.SubcategoryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface SubcategoryApi {

    @GET("/subcategories/{categoryId}")
    suspend fun getSubcategories(@Path("categoryId") categoryId: Long): List<SubcategoryDto>
}