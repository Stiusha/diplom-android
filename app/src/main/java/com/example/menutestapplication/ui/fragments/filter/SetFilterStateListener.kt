package com.example.menutestapplication.ui.fragments.filter

import com.example.menutestapplication.retrofit.dto.search.FilterDto

interface SetFilterStateListener {
    fun setMinPrice(minPrice: Long)
    fun setMaxPrice(maxPrice: Long)
    fun getFilterDtoKeyState(characteristicId: Long, characteristicValueId: Long): Boolean
    fun addFilterDtoKey(characteristicId: Long, characteristicValueId: Long)
    fun removeFilterDtoKey(characteristicId: Long, characteristicValueId: Long)
    fun cleanFilterDto(): FilterDto
}