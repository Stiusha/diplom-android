package com.example.diplom.retrofit.dto.filter

data class FilterViewDto(
    val minPrice: Long,
    val maxPrice: Long,
    val filterItems: List<FilterItemDto>
)
