package com.example.diplom.retrofit.configuration

import com.example.diplom.retrofit.repository.CategoryApi
import com.example.diplom.retrofit.repository.FilterApi
import com.example.diplom.retrofit.repository.ProductApi
import com.example.diplom.retrofit.repository.StringsApi
import com.example.diplom.retrofit.repository.SubcategoryApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebClient {

    private const val BASE_URL = "http://10.0.2.2:2004"
    private var retrofit: Retrofit? = null

    private fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    val categoryApi: CategoryApi get() = getClient().create(CategoryApi::class.java)
    val filterApi: FilterApi get() = getClient().create(FilterApi::class.java)
    val productApi: ProductApi get() = getClient().create(ProductApi::class.java)
    val stringsApi: StringsApi get() = getClient().create(StringsApi::class.java)
    val subcategoryApi: SubcategoryApi get() = getClient().create(SubcategoryApi::class.java)
}