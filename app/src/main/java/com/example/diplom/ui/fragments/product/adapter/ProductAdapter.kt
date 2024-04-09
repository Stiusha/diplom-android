package com.example.diplom.ui.fragments.product.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.diplom.databinding.ProductItemBinding
import com.example.diplom.retrofit.dto.ProductDto
import com.example.diplom.ui.parent.AbstractAdapter
import com.squareup.picasso.Picasso

class ProductAdapter(
    dataList: List<ProductDto>,
    private val clickListener: ProductItemClickListener
) :
    AbstractAdapter<ProductItemBinding, ProductDto>(dataList) {

    override fun bindViewHolder(binding: ProductItemBinding, data: ProductDto) {
        Picasso.get().load(data.productImage).into(binding.productItemImage)
        binding.productItemTitle.text = data.productName
        binding.productItemPrice.text = data.productPrice.toString() + "₴"
        binding.productItemRate.rating = data.productRate.toFloat()
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ProductItemBinding {
        return ProductItemBinding.inflate(inflater, parent, false)
    }

}