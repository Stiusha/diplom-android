package com.example.menutestapplication.retrofit.repository

import com.example.menutestapplication.retrofit.dto.ProductDto
import com.example.menutestapplication.retrofit.dto.search.FilterDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ProductApi {

    @POST("/products")
    suspend fun getProducts(@Body filterDto: FilterDto): List<ProductDto>
}