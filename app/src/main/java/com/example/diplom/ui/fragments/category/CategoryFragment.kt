package com.example.diplom.ui.fragments.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplom.MainActivity
import com.example.diplom.databinding.FragmentCategoryBinding
import com.example.diplom.retrofit.configuration.WebClient
import com.example.diplom.ui.fragments.category.adapter.CategoryAdapter
import com.example.diplom.ui.fragments.category.adapter.CategoryItemClickListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryFragment : Fragment(), CategoryItemClickListener {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        binding.categoryList.layoutManager = LinearLayoutManager(this.context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            val list = WebClient.categoryApi.getCategories()
            Log.i("CategoryFragment", "RESPONSE = $list")
            withContext(Dispatchers.Main) {
                binding.categoryList.adapter = CategoryAdapter(list, this@CategoryFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun chooseCategory(categoryId: Long) {
        val action =
            CategoryFragmentDirections.actionNavigationCategoryToNavigationSubcategory(categoryId)
        (requireActivity() as MainActivity).openFragmentAction(action)
    }
}