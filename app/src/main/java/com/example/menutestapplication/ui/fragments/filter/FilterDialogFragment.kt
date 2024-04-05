package com.example.menutestapplication.ui.fragments.filter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.menutestapplication.databinding.FragmentFilterDialogBinding
import com.example.menutestapplication.retrofit.configuration.WebClient
import com.example.menutestapplication.retrofit.dto.filter.FilterViewDto
import com.example.menutestapplication.retrofit.dto.search.FilterDto
import com.example.menutestapplication.retrofit.dto.search.FilterKeysDto
import com.example.menutestapplication.ui.fragments.filter.adapter.FilterAdapter
import com.example.menutestapplication.ui.fragments.filter.seekbar.PriceMaxChangeListener
import com.example.menutestapplication.ui.fragments.filter.seekbar.PriceMinChangeListener
import com.example.menutestapplication.ui.utils.LinearSpacingItemDecoration
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FilterDialogFragment : DialogFragment(), SetFilterStateListener {

    private var _binding: FragmentFilterDialogBinding? = null
    private val binding get() = _binding!!

    private lateinit var filterData: FilterDto

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("FilterDialogFragment", "START----------------------------")
        _binding = FragmentFilterDialogBinding.inflate(inflater, container, false)

        Log.i("FilterDialogFragment", "Arguments FILTER = $arguments")
        filterData = Gson().fromJson(arguments?.getString("filterData"), FilterDto::class.java)

//        filterDto = FilterDto(fromBundle(requireArguments()).subcategoryId, mutableListOf())

        binding.buttonYes.setOnClickListener {
            cleanFilterDto()
            val json: String = Gson().toJson(filterData)
            Log.i("FilterDialogFragment", "DIALOG RESPONSE = $arguments")
            setFragmentResult("dialogKey", bundleOf("filterData" to json))
            dismiss()
        }

        binding.buttonNo.setOnClickListener {
            dismiss()
        }

        binding.filterList.addItemDecoration(LinearSpacingItemDecoration(10))
        binding.filterList.layoutManager = LinearLayoutManager(this.context)
        binding.seekBarPriceMin.setOnSeekBarChangeListener(PriceMinChangeListener(binding, this))
        binding.seekBarPriceMax.setOnSeekBarChangeListener(PriceMaxChangeListener(binding, this))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {

//            val args = fromBundle(requireArguments())
            val filterViewDto: FilterViewDto =
                WebClient.filterApi.getFilter(filterData.subcategoryId)
//            Log.i("FilterDialogFragment", "RESPONSE FILTER = $list")


            withContext(Dispatchers.Main) {
                binding.seekBarPriceMin.max = filterViewDto.maxPrice.toInt()
                binding.seekBarPriceMin.min = filterViewDto.minPrice.toInt()

                if (filterData.minPrice == null) {
                    binding.seekBarPriceMin.progress = filterViewDto.minPrice.toInt()
                } else {
                    binding.seekBarPriceMin.progress = filterData.minPrice!!.toInt()
                }

                binding.filterMinPriceLeft.text = filterViewDto.minPrice.toString()
                binding.filterMinPriceRight.text = filterViewDto.maxPrice.toString()

                binding.seekBarPriceMax.max = filterViewDto.maxPrice.toInt()

                binding.filterMaxPriceRight.text = filterViewDto.maxPrice.toString()

                if (filterData.minPrice == null) {
                    binding.seekBarPriceMax.min = filterViewDto.minPrice.toInt()
                    binding.filterMaxPriceLeft.text = filterViewDto.minPrice.toString()
                } else {
                    binding.seekBarPriceMax.min = filterData.minPrice!!.toInt()
                    binding.filterMaxPriceLeft.text = filterData.minPrice!!.toString()
                }

                if (filterData.maxPrice == null) {
                    binding.seekBarPriceMax.progress = filterViewDto.maxPrice.toInt()
                } else {
                    binding.seekBarPriceMax.progress = filterData.maxPrice!!.toInt()
                }

                binding.filterList.adapter =
                    FilterAdapter(filterViewDto.filterItems, this@FilterDialogFragment)
            }
        }
    }

    override fun setMinPrice(minPrice: Long) {
        filterData.minPrice = minPrice
    }

    override fun setMaxPrice(maxPrice: Long) {
        filterData.maxPrice = maxPrice
    }

    override fun getFilterDtoKeyState(
        characteristicId: Long,
        characteristicValueId: Long
    ): Boolean {
        val findFilterKeysDto: FilterKeysDto? =
            filterData.filters.find { it.characteristicId == characteristicId }
        if (findFilterKeysDto != null) {
            val findCharacteristicValueId =
                findFilterKeysDto.characteristicValueIds.find { it == characteristicValueId }
            if (findCharacteristicValueId != null) {
                return true
            }
        }
        return false
    }


    override fun addFilterDtoKey(characteristicId: Long, characteristicValueId: Long) {
        val find: FilterKeysDto? =
            filterData.filters.find { it.characteristicId == characteristicId }
        if (find == null) {
            filterData.filters.add(
                FilterKeysDto(
                    characteristicId,
                    mutableListOf(characteristicValueId)
                )
            )
        } else {
            find.characteristicValueIds.add(characteristicValueId)
        }
    }

    override fun removeFilterDtoKey(characteristicId: Long, characteristicValueId: Long) {
        val findFilter: FilterKeysDto? =
            filterData.filters.find { it.characteristicId == characteristicId }
        if (findFilter != null) {
            val valueId = findFilter.characteristicValueIds.find { it == characteristicValueId }
            if (valueId != null) {
                findFilter.characteristicValueIds.remove(characteristicValueId)
            }
        }
    }

    override fun cleanFilterDto(): FilterDto {
        val filters: List<FilterKeysDto> = filterData.filters.filter {
            it.characteristicValueIds.isNotEmpty()
        }
        filterData.filters = filters.toMutableList()
        return filterData
    }

}
