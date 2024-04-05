package com.example.diplom.ui.fragments.filter.seekbar

import android.widget.SeekBar
import com.example.diplom.databinding.FragmentFilterDialogBinding
import com.example.diplom.ui.fragments.filter.SetFilterStateListener

class PriceMinChangeListener(
    private val binding: FragmentFilterDialogBinding,
    private val listener: SetFilterStateListener
) : SeekBar.OnSeekBarChangeListener {

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        binding.filterMinPriceValue.text = progress.toString()
        binding.filterMaxPriceLeft.text = progress.toString()
        binding.seekBarPriceMax.min = progress
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        listener.setMinPrice(seekBar?.progress!!.toLong())
    }
}