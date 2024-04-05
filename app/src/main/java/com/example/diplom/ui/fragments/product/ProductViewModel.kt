package com.example.diplom.ui.fragments.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diplom.retrofit.dto.ProductDto

class ProductViewModel : ViewModel() {

    private val _products = MutableLiveData<List<ProductDto>>().apply {
        value = mutableListOf()
    }
    val products: MutableLiveData<List<ProductDto>> = _products
}