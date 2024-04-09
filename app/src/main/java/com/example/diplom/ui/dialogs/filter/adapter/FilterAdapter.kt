package com.example.diplom.ui.dialogs.filter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplom.databinding.FilterItemBinding
import com.example.diplom.retrofit.dto.filter.FilterItemDto
import com.example.diplom.ui.dialogs.filter.SetFilterStateListener
import com.example.diplom.ui.parent.AbstractAdapter
import com.example.diplom.ui.utils.LinearSpacingItemDecoration

class FilterAdapter(dataList: List<FilterItemDto>, private val listener: SetFilterStateListener) :
    AbstractAdapter<FilterItemBinding, FilterItemDto>(dataList) {

    override fun bindViewHolder(binding: FilterItemBinding, data: FilterItemDto) {
        binding.filterItemName.text = data.characteristicName

        binding.filterItemList.addItemDecoration(LinearSpacingItemDecoration(10))
        binding.filterItemList.layoutManager = LinearLayoutManager(binding.root.context)
        binding.filterItemList.adapter =
            SubFilterAdapter(data.characteristicId, data.characteristicValues, listener)


//        Picasso.get().load(data.getImage()).into(binding.productItemImage)
//        binding.productItemTitle.text = data.getName()
//        binding.categoryItemContainer.setOnClickListener {
//            clickListener.chooseProduct(data.getId())
//        }
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): FilterItemBinding {
        return FilterItemBinding.inflate(inflater, parent, false)
    }

}