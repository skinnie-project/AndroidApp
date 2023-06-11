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
import com.company.skinnie.data.response.PayloadForgotPassword
import com.company.skinnie.databinding.FragmentForgotPasswordBinding


class ForgotPasswordFragment : Fragment() {
    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ForgotPasswordViewModel by viewModels()

    private var mIsShowPass = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            etNewPassword.setOnClickListener {
                mIsShowPass = !mIsShowPass
                showPassword(mIsShowPass)
            }
            etConfirmNewPassword.setOnClickListener {
                mIsShowPass = !mIsShowPass
                showPassword(mIsShowPass)
            }

            btnSubmit.setOnClickListener {
                val username = binding.etUsername.text.toString()
                val newPassword = binding.etNewPassword.text.toString()
                val confirmPassword = binding.etConfirmNewPassword.text.toString()

                when {
                    username.isEmpty() -> {
                        binding.etUsername.error = "Username tidak boleh kosong"
                    }
                    newPassword.isEmpty() -> {
                        binding.etNewPassword.error = "Password tidak boleh kosong"
                    }
                    newPassword != confirmPassword -> {
                        binding.etConfirmNewPassword.error = "Password tidak sama"
                    }
                    else -> {
                        binding.loading.visibility = View.VISIBLE
                        getData(PayloadForgotPassword(username, newPassword))
                    }
                }
                findNavController().navigate(ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment())
            }
        }
        showPassword(mIsShowPass)
    }

    private fun showPassword(isShow: Boolean) {
        if (isShow) {
            binding.etNewPassword.transformationMethod =
                HideReturnsTransformationMethod.getInstance()
            binding.etConfirmNewPassword.transformationMethod =
                HideReturnsTransformationMethod.getInstance()
        } else {
            binding.etNewPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.etConfirmNewPassword.transformationMethod =
                PasswordTransformationMethod.getInstance()
        }
        binding.etNewPassword.setSelection(binding.etNewPassword.text.toString().length)
    }

    private fun getData(payloadForgotPassword: PayloadForgotPassword) {
        viewModel.userForgotPassword(payloadForgotPassword).observe(viewLifecycleOwner) {
            binding.loading.visibility = View.GONE
            if (it != null && it.status == "success") {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                findNavController().navigate(ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment())
            } else {
                binding.loading.visibility = View.GONE
                Toast.makeText(requireContext(), it?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}