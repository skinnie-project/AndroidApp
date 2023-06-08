package com.company.skinnie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.skinnie.data.response.ResponseArticleItem
import com.company.skinnie.databinding.ListItemBinding

class ArticleAdapter(private val data: MutableList<ResponseArticleItem> = mutableListOf()) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>(){
    private var listener: ArticleListener? = null

    interface ArticleListener {
        fun onArticleSelected(item: ResponseArticleItem)
    }

    fun setListener(articleListener: ArticleListener) {
        listener = articleListener
    }

    fun setDataArticle(listArticle: List<ResponseArticleItem>) {
        this.data.clear()
        this.data.addAll(listArticle)
        notifyDataSetChanged()
    }

    inner class ArticleViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(article: ResponseArticleItem) {
            binding.apply {
//                Glide.with(itemView)
//                    .load(popular.urlNew)
//                    .into(ivPopularProduct)
                tvTitleArticle.text = article.judul
                tvWriterArticle.text = article.ditinjau

                root.setOnClickListener {
                    listener?.onArticleSelected(article)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = data.size
}