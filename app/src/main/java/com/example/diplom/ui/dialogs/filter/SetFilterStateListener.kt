package com.example.diplom.ui.dialogs.filter

import com.example.diplom.retrofit.dto.search.FilterDto

interface SetFilterStateListener {
    fun setMinPrice(minPrice: Long)
    fun setMaxPrice(maxPrice: Long)
    fun getFilterDtoKeyState(characteristicId: Long, characteristicValueId: Long): Boolean
    fun addFilterDtoKey(characteristicId: Long, characteristicValueId: Long)
    fun removeFilterDtoKey(characteristicId: Long, characteristicValueId: Long)
    fun cleanFilterDto(): FilterDto
}