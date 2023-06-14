package com.company.skinnie.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.skinnie.adapter.RecommendAdapter
import com.company.skinnie.databinding.ActivitySearchBinding
import com.company.skinnie.ui.detail.DetailProductActivity

class SearchActivity : AppCompatActivity(), (Int) -> Unit {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: RecommendAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RecommendAdapter(this)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(SearchViewModel::class.java)

        binding.apply {
            rvSearch.setHasFixedSize(true)
            rvSearch.layoutManager = LinearLayoutManager(this@SearchActivity)
            rvSearch.adapter = adapter

            searchView2.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    viewModel.findProduct(query.toString())
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    viewModel.findProduct(query.toString())
                    return true
                }

            })
        }

        viewModel.product.observe(this) {
            if (it != null) {
                adapter.setRecommend(it)
            }
        }


    }

    override fun invoke(id: Int) {
        startActivity(Intent(this, DetailProductActivity::class.java).apply {
            putExtra(DetailProductActivity.EXTRA_ID, id)
        })
    }
}