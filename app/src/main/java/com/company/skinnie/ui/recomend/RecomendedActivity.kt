package com.company.skinnie.ui.recomend

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.skinnie.adapter.RecommendAdapter
import com.company.skinnie.databinding.ActivityRecomendedBinding

class RecomendedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecomendedBinding
    private lateinit var recommendAdapter: RecommendAdapter
    private val viewModel: RecommendViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecomendedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //actionbar in activity
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val predict = intent.getStringExtra(EXTRA_PREDICT)
        binding.tvRecomended.text = predict

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        recommendAdapter = RecommendAdapter()
        binding.rvRecommend.apply {
            adapter = recommendAdapter
            layoutManager = LinearLayoutManager(this@RecomendedActivity)
        }
        getUser(predict!!)
    }

    private fun getUser(query: String) {
        viewModel.setPredict(query).observe(this) {
            if (it != null) {
                recommendAdapter.setRecommend(it)
            }
        }

    }

    companion object {
        const val EXTRA_PREDICT = "extra_predict"
    }
}