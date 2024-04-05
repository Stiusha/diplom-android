package com.example.diplom.ui.fragments.product

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplom.MainActivity
import com.example.diplom.databinding.FragmentProductBinding
import com.example.diplom.retrofit.configuration.WebClient
import com.example.diplom.retrofit.dto.ProductDto
import com.example.diplom.retrofit.dto.search.FilterDto
import com.example.diplom.ui.fragments.product.adapter.ProductAdapter
import com.example.diplom.ui.fragments.product.adapter.ProductItemClickListener
import com.example.diplom.ui.utils.LinearSpacingItemDecoration
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductFragment : Fragment(), ProductItemClickListener {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    private var _viewModel: ProductViewModel? = null
    private val viewModel get() = _viewModel!!

    private var _filterDto: FilterDto? = null
    private val filterData get() = _filterDto!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("ProductFragment", "START----------------------------")
        _viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        if (_filterDto == null) {
            _filterDto =
                FilterDto(arguments?.getLong("subcategoryId")!!, null, null, mutableListOf())
        }

        binding.productList.addItemDecoration(LinearSpacingItemDecoration(10))
        binding.productList.layoutManager = LinearLayoutManager(this.context)

        viewModel.products.observe(viewLifecycleOwner) {
            binding.productList.adapter = ProductAdapter(it, this)
        }

        binding.filter.setOnClickListener {
            Log.i("ProductFragment", "Arguments PRODUCTS = $arguments")
            val json = Gson().toJson(filterData)
            val action: NavDirections =
                ProductFragmentDirections.actionNavigationProductToNavigationFilterDialog(json)
            (requireActivity() as MainActivity).openFragmentAction(action)
        }

        setFragmentResultListener("dialogKey") { _, bundle ->
            val json = bundle.getString("filterData")
            val filterDto = Gson().fromJson(json, FilterDto::class.java)
            _filterDto = filterDto
            viewProducts(filterDto)
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val subcategoryId = arguments?.getLong("subcategoryId")
//        val filterDto = FilterDto(subcategoryId!!, null, null, mutableListOf())
        Log.i("ProductFragment", "REQUEST = $filterData")
        viewProducts(filterData)
    }

    private fun viewProducts(filterDto: FilterDto) {
        CoroutineScope(Dispatchers.IO).launch {
            val list: List<ProductDto> = WebClient.productApi.getProducts(filterDto)
            Log.i("ProductFragment", "RESPONSE PRODUCTS = $list")
            withContext(Dispatchers.Main) {
                viewModel.products.value = list
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun chooseProduct(productId: Long) {
        Toast.makeText(this.context, "$productId", Toast.LENGTH_SHORT).show();
    }
}