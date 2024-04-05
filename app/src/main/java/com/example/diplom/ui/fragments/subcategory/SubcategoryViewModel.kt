package com.example.diplom.ui.fragments.subcategory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SubcategoryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: MutableLiveData<String> = _text
}