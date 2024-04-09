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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("ProductFragment", "START----------------------------")
        _viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.filterDto.value =
            FilterDto(arguments?.getLong("subcategoryId")!!, null, null, mutableListOf())

        binding.productList.addItemDecoration(LinearSpacingItemDecoration(10))
        binding.productList.layoutManager = LinearLayoutManager(this.context)

        viewModel.products.observe(viewLifecycleOwner) {
            binding.productList.adapter = ProductAdapter(it, this)
        }

        viewModel.filterDto.observe(viewLifecycleOwner) {
            viewProducts()
        }

        viewModel.sortId.observe(viewLifecycleOwner) {
            viewProducts()
        }

        binding.filter.setOnClickListener {
            Log.i("ProductFragment", "Arguments PRODUCTS = $arguments")
            val json = Gson().toJson(viewModel.filterDto.value)
            val action: NavDirections =
                ProductFragmentDirections.actionNavigationProductToNavigationFilterDialog(json)
            (requireActivity() as MainActivity).openFragmentAction(action)
        }

        binding.sort.setOnClickListener {
            val action: NavDirections =
                ProductFragmentDirections.actionNavigationProductToSortDialogFragment(viewModel.sortId.value!!)
            (requireActivity() as MainActivity).openFragmentAction(action)
        }

        setFragmentResultListener("dialogFilterKey") { _, bundle ->
            val json = bundle.getString("filterData")
            val filterDto = Gson().fromJson(json, FilterDto::class.java)
            viewModel.filterDto.value = filterDto
        }

        setFragmentResultListener("dialogSortKey") { _, bundle ->
            val sortId = bundle.getLong("sortId")
            viewModel.sortId.value = sortId
        }
    }

    private fun viewProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            val list: List<ProductDto> = WebClient.productApi.getProducts(
                viewModel.filterDto.value!!,
                viewModel.sortId.value!!
            )
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