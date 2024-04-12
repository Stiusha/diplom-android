package com.example.diplom.ui.fragments.product.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.diplom.retrofit.dto.ProductDto

class ProductDiffCallback(
    private val oldList: List<ProductDto>,
    private val newList: List<ProductDto>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].productId == newList[newItemPosition].productId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}