package com.example.menutestapplication.retrofit.dto.search

data class FilterDto(
    val subcategoryId: Long,
    var minPrice: Long?,
    var maxPrice: Long?,
    var filters: MutableList<FilterKeysDto>
)