package com.example.diplom.ui.dialogs.filter.seekbar

import android.widget.SeekBar
import com.example.diplom.databinding.FragmentFilterDialogBinding
import com.example.diplom.ui.dialogs.filter.SetFilterStateListener

class PriceMaxChangeListener(
    private val binding: FragmentFilterDialogBinding,
    private val listener: SetFilterStateListener
) : SeekBar.OnSeekBarChangeListener {

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        binding.filterMaxPriceValue.text = progress.toString()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        listener.setMaxPrice(seekBar?.progress!!.toLong())
    }
}