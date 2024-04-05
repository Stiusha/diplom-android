package com.example.menutestapplication.ui.fragments.filter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.menutestapplication.databinding.FilterSubitemBinding
import com.example.menutestapplication.retrofit.dto.filter.FilterValueDto
import com.example.menutestapplication.ui.fragments.filter.SetFilterStateListener
import com.example.menutestapplication.ui.parent.AbstractAdapter

class SubFilterAdapter(
    private val characteristicId: Long,
    dataList: List<FilterValueDto>,
    private val listener: SetFilterStateListener
) :
    AbstractAdapter<FilterSubitemBinding, FilterValueDto>(dataList) {

    override fun bindViewHolder(binding: FilterSubitemBinding, data: FilterValueDto) {
        binding.checkBox.text = data.characteristicValue
        binding.checkBox.isChecked =
            listener.getFilterDtoKeyState(characteristicId, data.characteristicValueId)
        binding.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                listener.addFilterDtoKey(characteristicId, data.characteristicValueId)
            } else {
                listener.removeFilterDtoKey(characteristicId, data.characteristicValueId)
            }
        }
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): FilterSubitemBinding {
        return FilterSubitemBinding.inflate(inflater, parent, false)
    }

}