package com.example.diplom.ui.dialogs.sort

import android.widget.CheckBox
import android.widget.CompoundButton
import com.example.diplom.databinding.FragmentSortDialogBinding

class CheckBoxCheckedChangeListener(
    private val binding: FragmentSortDialogBinding,
    private val viewModel: SortDialogViewModel
) : CompoundButton.OnCheckedChangeListener {

    override fun onCheckedChanged(checkedCheckbox: CompoundButton, state: Boolean) {
        if (state) {
            for (i in 0 until binding.dialogSortRadioGroup.childCount) {
                val checkbox: CheckBox = binding.dialogSortRadioGroup.getChildAt(i) as CheckBox
                checkbox.isChecked = checkbox.id == checkedCheckbox.id
                if (checkbox.id == checkedCheckbox.id) {
                    viewModel.sortId.value = i + 1L
                }
            }
        }
    }
}