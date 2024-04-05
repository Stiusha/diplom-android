package com.example.diplom.ui.fragments.subcategory

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplom.MainActivity
import com.example.diplom.databinding.FragmentSubcategoryBinding
import com.example.diplom.retrofit.configuration.WebClient
import com.example.diplom.ui.fragments.subcategory.adapter.SubcategoryAdapter
import com.example.diplom.ui.fragments.subcategory.adapter.SubcategoryItemClickListener
import com.example.diplom.ui.utils.LinearSpacingItemDecoration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SubcategoryFragment : Fragment(), SubcategoryItemClickListener {

    private var _binding: FragmentSubcategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val subcategoryViewModel = ViewModelProvider(this).get(SubcategoryViewModel::class.java)

        _binding = FragmentSubcategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome2
//        subcategoryViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }


//        textView.setOnClickListener {
//            val action: NavDirections = SubcategoryFragmentDirections.actionNavigationSubcategoryToNavigationProduct(null, 1)
//            (requireActivity() as MainActivity).openFragmentAction(action)
//        }

        binding.subcategoryList.addItemDecoration(LinearSpacingItemDecoration(10))
        binding.subcategoryList.layoutManager = LinearLayoutManager(this.context)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            val categoryId = arguments?.getLong("categoryId")
            val list = WebClient.subcategoryApi.getSubcategories(categoryId!!)
            Log.i("MainActivity", "RESPONSE = $list")

            withContext(Dispatchers.Main) {
                binding.subcategoryList.adapter = SubcategoryAdapter(list, this@SubcategoryFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun chooseSubcategory(subcategoryId: Long) {
        val action = SubcategoryFragmentDirections.actionNavigationSubcategoryToNavigationProduct(
            subcategoryId
        )
        (requireActivity() as MainActivity).openFragmentAction(action)
    }
}