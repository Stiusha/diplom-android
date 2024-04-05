package com.example.diplom.retrofit.dto.search

data class FilterKeysDto(
    val characteristicId: Long,
    val characteristicValueIds: MutableList<Long>
)