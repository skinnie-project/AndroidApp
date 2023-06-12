package com.company.skinnie.ui.auth

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.company.skinnie.data.response.PayloadRegister
import com.company.skinnie.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterViewModel by viewModels()

    private var mIsShowPass = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            etPassword.setOnClickListener {
                mIsShowPass = !mIsShowPass
                showPassword(mIsShowPass)
            }

            etConfirmPassword.setOnClickListener {
                mIsShowPass = !mIsShowPass
                showPassword(mIsShowPass)
            }

            btnRegister.setOnClickListener {
                val username = binding.etUsername.text.toString()
                val password = binding.etPassword.text.toString()
                val confirmPassword = binding.etConfirmPassword.text.toString()
                val email = binding.etEmail.text.toString()
                val name = binding.etName.text.toString()

                when {
                    username.isEmpty() -> {
                        binding.etUsername.error = "Username tidak boleh kosong"
                    }
                    password.isEmpty() -> {
                        binding.etPassword.error = "Password tidak boleh kosong"
                    }
                    confirmPassword.isEmpty() -> {
                        binding.etConfirmPassword.error = "Confirm Password tidak boleh kosong"
                    }
                    email.isEmpty() -> {
                        binding.etEmail.error = "Email tidak boleh kosong"
                    }
                    name.isEmpty() -> {
                        binding.etName.error = "Name tidak boleh kosong"
                    }
                    else -> {
                        binding.loading.visibility = View.VISIBLE
                        getData(
                            PayloadRegister(
                                username = username,
                                password = password,
                                email = email,
                                nama = name
                            )
                        )
                    }
                }
            }
        }
        showPassword(mIsShowPass)
    }

    private fun showPassword(isShow: Boolean) {
        if (isShow) {
            binding.etPassword.transformationMethod =
                HideReturnsTransformationMethod.getInstance()
            binding.etConfirmPassword.transformationMethod =
                HideReturnsTransformationMethod.getInstance()
        } else {
            binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.etConfirmPassword.transformationMethod =
                PasswordTransformationMethod.getInstance()
        }
        binding.etPassword.setSelection(binding.etPassword.text.toString().length)
    }

    private fun getData(payloadRegister: PayloadRegister) {
        viewModel.register(payloadRegister).observe(viewLifecycleOwner) {
            if (it != null && it.status == "success"){
                binding.loading.visibility = View.GONE
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
            } else {
                binding.loading.visibility = View.GONE
                Toast.makeText(
                    requireContext(),
                    it!!.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}