package com.example.diplom.retrofit.repository

import com.example.diplom.retrofit.dto.ProductDto
import com.example.diplom.retrofit.dto.search.FilterDto
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductApi {

    @POST("/products")
    suspend fun getProducts(@Body filterDto: FilterDto, @Query("sort") sort: Long): List<ProductDto>
}