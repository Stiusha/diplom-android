package com.example.menutestapplication.retrofit.repository

import com.example.menutestapplication.retrofit.dto.filter.FilterViewDto
import retrofit2.http.GET
import retrofit2.http.Path

interface FilterApi {

    @GET("/filter/{subcategoryId}")
    suspend fun getFilter(@Path("subcategoryId") subcategoryId: Long): FilterViewDto
}