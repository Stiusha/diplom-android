package com.example.menutestapplication.retrofit.repository

import retrofit2.http.GET

interface StringsApi {

    @GET("/strings")
    suspend fun strings(): Map<String, String>
}