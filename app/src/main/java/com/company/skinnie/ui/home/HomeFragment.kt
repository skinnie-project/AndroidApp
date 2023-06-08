package com.company.skinnie.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.skinnie.Preferences
import com.company.skinnie.adapter.ArticleAdapter
import com.company.skinnie.adapter.PopularAdapter
import com.company.skinnie.data.response.ResponseArticleItem
import com.company.skinnie.data.response.ResponsePopularItem
import com.company.skinnie.databinding.FragmentHomeBinding
import com.company.skinnie.ui.article.ArticleActivity
import com.company.skinnie.ui.detail.DetailProductActivity
import com.company.skinnie.ui.manual_input.ManualInputActivity

class HomeFragment : Fragment(), ArticleAdapter.ArticleListener, PopularAdapter.PopularListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapterPopular: PopularAdapter
    private lateinit var adapterArticle: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //value getPreferences
        val name = Preferences(requireContext()).getValues("name")

        binding.tvUser.text = name

        //get data popular
        adapterPopular = PopularAdapter()
        adapterPopular.setListener(this)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(HomeViewModel::class.java)

        viewModel.popular.observe(viewLifecycleOwner) {
            adapterPopular.setDataPopular(it)
        }

        //get data article
        adapterArticle = ArticleAdapter()
        adapterArticle.setListener(this)

        viewModel.article.observe(viewLifecycleOwner) {
            adapterArticle.setDataArticle(it)
        }


        binding.apply {
            rvPopuler.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            rvPopuler.setHasFixedSize(true)
            rvPopuler.adapter = adapterPopular

            rvArtikel.layoutManager = LinearLayoutManager(activity)
            rvArtikel.setHasFixedSize(true)
            rvArtikel.adapter = adapterArticle
        }

        binding.tvArticle.setOnClickListener {
            startActivity(Intent(requireContext(), ArticleActivity::class.java))

        }

        binding.btnManualInput.setOnClickListener {
            startActivity(Intent(requireContext(), ManualInputActivity::class.java))
        }

    }

    override fun onArticleSelected(item: ResponseArticleItem) {
        Log.d("", "${item.id} ${item.judul} ${item.sumber}")
        openUrl(item.sumber.toString())
    }

    fun openUrl (url : String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    override fun onPopularSelected(item: ResponsePopularItem) {
        val intent = Intent(requireContext(), DetailProductActivity::class.java).apply {
            putExtra(DetailProductActivity.EXTRA_ID, item.id)
        }
        startActivity(intent)
    }


}