package com.example.diplom.ui.fragments.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom.databinding.CategoryItemBinding
import com.example.diplom.retrofit.dto.CategoryDto
import com.squareup.picasso.Picasso

class CategoryAdapter(
    private val dataList: List<CategoryDto>,
    private val clickListener: CategoryItemClickListener
) : RecyclerView.Adapter<CategoryAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CategoryDto) {
            Picasso.get().load(data.getImage()).into(binding.categoryItemImage)
            binding.categoryItemTitle.text = data.getName()
            binding.categoryItemContainer.setOnClickListener {
                clickListener.chooseCategory(data.getId())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val inflate = CategoryItemBinding.inflate(inflater, parent, false);
        return ItemViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}