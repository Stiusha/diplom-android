package com.example.menutestapplication.ui.fragments.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.menutestapplication.databinding.CategoryItemBinding
import com.example.menutestapplication.retrofit.dto.Item
import com.example.menutestapplication.ui.parent.AbstractAdapter
import com.squareup.picasso.Picasso

class CategoryAdapter(dataList: List<Item>, private val clickListener: CategoryItemClickListener) :
    AbstractAdapter<CategoryItemBinding, Item>(dataList) {

    override fun bindViewHolder(binding: CategoryItemBinding, data: Item) {
        Picasso.get().load(data.getImage()).into(binding.categoryItemImage)
        binding.categoryItemTitle.text = data.getName()
        binding.categoryItemContainer.setOnClickListener {
            clickListener.chooseCategory(data.getId())
        }
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): CategoryItemBinding {
        return CategoryItemBinding.inflate(inflater, parent, false)
    }

}