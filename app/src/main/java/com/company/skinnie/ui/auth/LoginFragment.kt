package com.company.skinnie.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.company.skinnie.NavigationActivity
import com.company.skinnie.Preferences
import com.company.skinnie.data.response.PayloadLogin
import com.company.skinnie.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val name = Preferences(requireContext()).getValues("name").toString()

        if (name.isNotEmpty()) {
            startActivity(Intent(requireContext(), NavigationActivity::class.java))
            activity?.finish()
        }

        binding.tvForgotPassword.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment())
        }

        binding.tvRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            when {
                username.isEmpty() -> {
                    binding.etUsername.error = "Username tidak boleh kosong"
                }
                password.isEmpty() -> {
                    binding.etPassword.error = "Password tidak boleh kosong"
                }
                else -> {
                    binding.loading.visibility = View.VISIBLE
                    getData(PayloadLogin(username, password))
                }
            }
        }
    }

    private fun getData(payloadLogin: PayloadLogin) {
        viewModel.userLogin(payloadLogin).observe(viewLifecycleOwner) {
            binding.loading.visibility = View.GONE
            if (it != null && it.status == "success") {
                //preferences untuk menyimpan data user
                val preferences = Preferences(requireContext())

                //buat nyimpan data usernamenya
                preferences.setValues("name", it.nama!!)

                //action to activity
                startActivity(Intent(requireContext(), NavigationActivity::class.java))
                activity?.finish()
            } else {
                binding.loading.visibility = View.GONE
                Toast.makeText(requireContext(), "Username atau password salah", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}