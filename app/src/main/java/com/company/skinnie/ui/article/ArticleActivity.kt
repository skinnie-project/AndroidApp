package com.company.skinnie.ui.article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.company.skinnie.R
import com.company.skinnie.databinding.ActivityArticleBinding

class ArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar3.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}