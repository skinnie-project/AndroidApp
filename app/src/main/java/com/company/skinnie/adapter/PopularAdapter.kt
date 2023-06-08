package com.company.skinnie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.company.skinnie.data.response.ResponseArticleItem
import com.company.skinnie.data.response.ResponsePopularItem
import com.company.skinnie.databinding.ItemRecommendBinding
import com.company.skinnie.databinding.ListPopularItemBinding

class PopularAdapter (private val data: MutableList<ResponsePopularItem> = mutableListOf()) :
    RecyclerView.Adapter<PopularAdapter.PopularViewHolder>(){
    private var listener: PopularListener? = null

    interface PopularListener {
        fun onPopularSelected(item: ResponsePopularItem)
    }

    fun setListener(popularListener: PopularListener) {
        listener = popularListener
    }

    fun setDataPopular(listPopular: List<ResponsePopularItem>) {
        this.data.clear()
        this.data.addAll(listPopular)
        notifyDataSetChanged()
    }

    inner class PopularViewHolder(val binding: ListPopularItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(popular: ResponsePopularItem) {
            binding.apply {
                Glide.with(itemView)
                    .load(popular.urlNew)
                    .into(ivPopularProduct)
                tvPopularProduct.text = popular.productName

                root.setOnClickListener {
                    listener?.onPopularSelected(popular)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val view = ListPopularItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = data.size
}