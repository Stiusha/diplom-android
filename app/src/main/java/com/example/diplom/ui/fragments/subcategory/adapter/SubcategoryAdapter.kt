package com.example.diplom.ui.fragments.subcategory.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.diplom.databinding.SubcategoryItemBinding
import com.example.diplom.retrofit.dto.Item
import com.example.diplom.ui.parent.AbstractAdapter
import com.squareup.picasso.Picasso

class SubcategoryAdapter(
    dataList: List<Item>,
    private val clickListener: SubcategoryItemClickListener
) :
    AbstractAdapter<SubcategoryItemBinding, Item>(dataList) {

    override fun bindViewHolder(binding: SubcategoryItemBinding, data: Item) {
        Picasso.get().load(data.getImage()).into(binding.subcategoryItemImage)
        binding.subcategoryItemTitle.text = data.getName()
        binding.subcategoryItemContainer.setOnClickListener {
            clickListener.chooseSubcategory(data.getId())
        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): SubcategoryItemBinding {
        return SubcategoryItemBinding.inflate(inflater, parent, false)
    }

}