package com.company.skinnie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.company.skinnie.data.response.ResponseRecommend
import com.company.skinnie.databinding.ItemRecommendBinding

class RecommendAdapter : RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder>() {

    private val recommends = arrayListOf<ResponseRecommend.ResponseRecommendItem?>()
    fun setRecommend(recommendList: List<ResponseRecommend.ResponseRecommendItem?>) {
        recommends.clear()
        recommends.addAll(recommendList)
        notifyDataSetChanged()
    }

    inner class RecommendViewHolder(private val binding: ItemRecommendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recommend: ResponseRecommend.ResponseRecommendItem?) {
            binding.apply {
                if (recommend != null) {
                    Glide.with(itemView.context)
                        .load(recommend.urlNew)
                        .into(ivDummyProduct)
                    tvMerekProduct.text = recommend.brand
                    tvDescProduct.text = recommend.description
                    tvPriceProduct.text = recommend.price
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val view = ItemRecommendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendViewHolder(view)
    }

    override fun getItemCount(): Int = recommends.size

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        holder.bind(recommends[position])
    }
}