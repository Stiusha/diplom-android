package com.example.diplom.retrofit.dto.filter

data class FilterItemDto(
    val characteristicId: Long,
    val characteristicName: String,
    val characteristicValues: List<FilterValueDto>
)
