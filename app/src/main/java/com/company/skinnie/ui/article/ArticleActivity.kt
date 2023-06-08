package com.company.skinnie.ui.article

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.skinnie.R
import com.company.skinnie.adapter.ArticleAdapter
import com.company.skinnie.data.response.ResponseArticleItem
import com.company.skinnie.databinding.ActivityArticleBinding
import com.company.skinnie.ui.home.HomeViewModel

class ArticleActivity : AppCompatActivity(), ArticleAdapter.ArticleListener {

    private lateinit var binding: ActivityArticleBinding
    private lateinit var adapter: ArticleAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar3.setNavigationOnClickListener {
            onBackPressed()
        }

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(HomeViewModel::class.java)

        adapter = ArticleAdapter()
        adapter.setListener(this)

        viewModel.article.observe(this) {
            adapter.setDataArticle(it)
        }

        binding.apply {
            rvArticle.layoutManager = LinearLayoutManager(this@ArticleActivity)
            rvArticle.setHasFixedSize(true)
            rvArticle.adapter = adapter
        }
    }

    override fun onArticleSelected(item: ResponseArticleItem) {
        openUrl(item.sumber.toString())
    }

    fun openUrl (url : String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }
}