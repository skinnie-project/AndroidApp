package com.company.skinnie.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.company.skinnie.Preferences
import com.company.skinnie.databinding.FragmentHomeBinding
import com.company.skinnie.ui.article.ArticleActivity
import com.company.skinnie.ui.manual_input.ManualInputActivity

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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

        binding.tvArticle.setOnClickListener {
            startActivity(Intent(requireContext(), ArticleActivity::class.java))
        }

        binding.btnManualInput.setOnClickListener {
            startActivity(Intent(requireContext(), ManualInputActivity::class.java))
        }

    }
}