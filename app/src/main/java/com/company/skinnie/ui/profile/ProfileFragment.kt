package com.company.skinnie.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.company.skinnie.Preferences
import com.company.skinnie.databinding.FragmentProfileBinding
import com.company.skinnie.ui.auth.AuthActivity
import com.company.skinnie.ui.saran.SaranActivity
import com.company.skinnie.ui.wishlist.WishlistActivity


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //value getPreferences
        val name = Preferences(requireContext()).getValues("name")

        binding.tvNameUser.text = name

        binding.cvKeluar.setOnClickListener {
            Preferences(requireContext()).clearValues()
            startActivity(Intent(requireContext(), AuthActivity::class.java))
            activity?.finish()
        }

        binding.cvFavorite.setOnClickListener {
            startActivity(Intent(requireContext(), WishlistActivity::class.java))
        }

        binding.btnSaran.setOnClickListener {
            startActivity(Intent(requireContext(), SaranActivity::class.java))
        }
    }
}