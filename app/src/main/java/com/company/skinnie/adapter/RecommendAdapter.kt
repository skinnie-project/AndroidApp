package com.company.skinnie.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.company.skinnie.data.response.ResponsePopularItem
import com.company.skinnie.databinding.ItemRecommendBinding

class RecommendAdapter(val clickListener: (Int) -> Unit) :
    RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder>() {

    private val recommends = arrayListOf<ResponsePopularItem?>()
    fun setRecommend(recommendList: List<ResponsePopularItem?>) {
        recommends.clear()
        recommends.addAll(recommendList)
        notifyDataSetChanged()
    }

    inner class RecommendViewHolder(private val binding: ItemRecommendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(recommend: ResponsePopularItem?) {
            binding.apply {
                if (recommend != null) {
                    Glide.with(itemView.context)
                        .load(recommend.urlNew)
                        .into(ivDummyProduct)
                    tvMerekProduct.text = recommend.brand
                    tvDescProduct.text = recommend.description
                    if (recommend.price != null) {
                        tvPriceProduct.text = "Rp ${recommend.price}"
                    } else {
                        tvPriceProduct.text = "Rp 0"
                    }
                }
                itemView.setOnClickListener {
                    clickListener.invoke(recommend!!.id!!)
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