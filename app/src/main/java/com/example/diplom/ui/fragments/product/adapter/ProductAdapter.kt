package com.example.diplom.ui.fragments.product.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom.databinding.ProductItemBinding
import com.example.diplom.retrofit.dto.ProductDto
import com.squareup.picasso.Picasso

class ProductAdapter(
    private var dataList: List<ProductDto>,
    private val clickListener: ProductItemClickListener
) : RecyclerView.Adapter<ProductAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProductDto) {
            Picasso.get().load(data.productImage).into(binding.productItemImage)
            binding.productItemTitle.text = data.productName
            binding.productItemPrice.text = data.productPrice.toString() + "₴"
            binding.productItemRate.rating = data.productRate.toFloat()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val inflate = ProductItemBinding.inflate(inflater, parent, false);
        return ItemViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    public fun updateData(dataList: List<ProductDto>) {
        val diffCallback = ProductDiffCallback(this.dataList, dataList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.dataList = dataList
        diffResult.dispatchUpdatesTo(this)
    }
}