package com.example.menutestapplication.retrofit.dto.search

data class FilterKeysDto(
    val characteristicId: Long,
    val characteristicValueIds: MutableList<Long>
)