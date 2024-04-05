package com.example.menutestapplication.retrofit.repository

import com.example.menutestapplication.retrofit.dto.SubcategoryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface SubcategoryApi {

    @GET("/subcategories/{categoryId}")
    suspend fun getSubcategories(@Path("categoryId") categoryId: Long): List<SubcategoryDto>
}