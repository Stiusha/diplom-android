package com.example.menutestapplication.ui.fragments.product.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.menutestapplication.databinding.ProductItemBinding
import com.example.menutestapplication.retrofit.dto.Item
import com.example.menutestapplication.ui.parent.AbstractAdapter
import com.squareup.picasso.Picasso

class ProductAdapter(dataList: List<Item>, private val clickListener: ProductItemClickListener) :
    AbstractAdapter<ProductItemBinding, Item>(dataList) {

    override fun bindViewHolder(binding: ProductItemBinding, data: Item) {
        Picasso.get().load(data.getImage()).into(binding.productItemImage)
        binding.productItemTitle.text = data.getName()
        binding.categoryItemContainer.setOnClickListener {
            clickListener.chooseProduct(data.getId())
        }
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ProductItemBinding {
        return ProductItemBinding.inflate(inflater, parent, false)
    }

}