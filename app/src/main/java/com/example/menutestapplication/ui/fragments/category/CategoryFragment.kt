package com.example.menutestapplication.ui.fragments.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.menutestapplication.MainActivity
import com.example.menutestapplication.databinding.FragmentCategoryBinding
import com.example.menutestapplication.retrofit.configuration.WebClient
import com.example.menutestapplication.ui.fragments.category.adapter.CategoryAdapter
import com.example.menutestapplication.ui.fragments.category.adapter.CategoryItemClickListener
import com.example.menutestapplication.ui.utils.LinearSpacingItemDecoration
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
        val categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textCategory
//        categoryViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

//        textView.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putLong("categoryId", 1L)
//            (requireActivity() as MainActivity)
//                .openFragment(R.id.action_navigation_category_to_navigation_subcategory, bundle)
//        }
        binding.categoryList.addItemDecoration(LinearSpacingItemDecoration(10))
        binding.categoryList.layoutManager = LinearLayoutManager(this.context)


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            val list = WebClient.categoryApi.getCategories()
            Log.i("MainActivity", "RESPONSE = $list")

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