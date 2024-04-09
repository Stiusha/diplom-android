package com.example.diplom.ui.dialogs.sort

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import com.example.diplom.databinding.FragmentSortDialogBinding

class SortDialogFragment : DialogFragment() {

    private var _binding: FragmentSortDialogBinding? = null
    private val binding get() = _binding!!

    private var _viewModel: SortDialogViewModel? = null
    private val viewModel get() = _viewModel!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("SortDialogFragment", "START----------------------------")
        _binding = FragmentSortDialogBinding.inflate(inflater, container, false)
        _viewModel = ViewModelProvider(this)[SortDialogViewModel::class.java]
        viewModel.sortId.value = arguments?.getLong("sortId")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listener = CheckBoxCheckedChangeListener(binding, viewModel)
        binding.dialogSortCheapToExpensive.setOnCheckedChangeListener(listener)
        binding.dialogSortExpensiveToCheap.setOnCheckedChangeListener(listener)
        binding.dialogSortByRating.setOnCheckedChangeListener(listener)

        when (viewModel.sortId.value) {
            1L -> binding.dialogSortCheapToExpensive.isChecked = true
            2L -> binding.dialogSortExpensiveToCheap.isChecked = true
            3L -> binding.dialogSortByRating.isChecked = true
        }

        binding.save.setOnClickListener {
            setFragmentResult("dialogSortKey", bundleOf("sortId" to viewModel.sortId.value))
            dismiss()
        }

        binding.exit.setOnClickListener {
            dismiss()
        }
    }
}