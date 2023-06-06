package com.company.skinnie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.skinnie.databinding.ItemRecommendBinding
import com.company.skinnie.model.ProductModel

class RecommendAdapter(): RecyclerView.Adapter<RecommendAdapter.ViewHolder>() {

    private val recommends = arrayListOf<ProductModel>()

    fun setRecommend(list: ArrayList<ProductModel>){
        recommends.clear()
        recommends.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding : ItemRecommendBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : ProductModel) {
            binding.apply {
                ivDummyProduct.setImageResource(item.image)
                tvMerekProduct.text = item.brand
                tvDescProduct.text = item.desc
                tvPriceProduct.text = item.price
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRecommendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = recommends.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recommends[position])
    }
}