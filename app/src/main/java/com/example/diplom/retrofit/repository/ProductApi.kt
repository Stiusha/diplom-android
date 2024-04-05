package com.example.diplom.retrofit.repository

import com.example.diplom.retrofit.dto.ProductDto
import com.example.diplom.retrofit.dto.search.FilterDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ProductApi {

    @POST("/products")
    suspend fun getProducts(@Body filterDto: FilterDto): List<ProductDto>
}