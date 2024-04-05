package com.example.diplom.ui.parent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class AbstractAdapter<B : ViewBinding, T>(private val dataList: List<T>) :
    RecyclerView.Adapter<AbstractAdapter<B, T>.ItemViewHolder>() {

    inner class ItemViewHolder(private val binding: B) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: T) {
            bindViewHolder(binding, data)
        }
    }

    abstract fun bindViewHolder(binding: B, data: T)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = createBinding(inflater, parent)
        return ItemViewHolder(binding)
    }

    abstract fun createBinding(inflater: LayoutInflater, parent: ViewGroup): B

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}