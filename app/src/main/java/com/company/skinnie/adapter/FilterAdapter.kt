package com.company.skinnie.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.company.skinnie.data.response.ResponseFilter
import com.company.skinnie.databinding.ItemRecommendBinding

class FilterAdapter(val clickListener: (Int) -> Unit) :
    RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {
    private val filters = arrayListOf<ResponseFilter.ResponseFilterItem?>()

    fun setFilter(filterList: List<ResponseFilter.ResponseFilterItem?>) {
        filters.clear()
        filters.addAll(filterList)
        notifyDataSetChanged()
    }

    inner class FilterViewHolder(private val binding: ItemRecommendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(filter: ResponseFilter.ResponseFilterItem?) {
            binding.apply {
                if (filter != null) {
                    Glide.with(itemView.context)
                        .load(filter.urlNew)
                        .into(ivDummyProduct)
                    tvMerekProduct.text = filter.brand
                    tvDescProduct.text = filter.description
                    if (filter.price != null) {
                        tvPriceProduct.text = "Rp ${filter.price}"
                    } else {
                        tvPriceProduct.text = "Rp 0"
                    }
                }
                itemView.setOnClickListener {
                    clickListener.invoke(filter!!.id!!)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val view = ItemRecommendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilterViewHolder(view)
    }

    override fun getItemCount(): Int = filters.size

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bind(filters[position])
    }
}
