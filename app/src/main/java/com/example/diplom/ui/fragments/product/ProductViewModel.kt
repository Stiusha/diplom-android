package com.example.diplom.ui.fragments.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diplom.retrofit.dto.ProductDto
import com.example.diplom.retrofit.dto.search.FilterDto

class ProductViewModel : ViewModel() {

    private val _products = MutableLiveData<List<ProductDto>>().apply {
        value = mutableListOf()
    }
    val products: MutableLiveData<List<ProductDto>> = _products


    private val _sortId = MutableLiveData<Long>().apply {
        value = -1
    }
    val sortId: MutableLiveData<Long> = _sortId


    val subcategoryId: MutableLiveData<Long> = MutableLiveData<Long>()
    val filterDto = MutableLiveData<FilterDto>()
}