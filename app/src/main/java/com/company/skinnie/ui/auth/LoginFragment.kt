package com.company.skinnie.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.company.skinnie.NavigationActivity
import com.company.skinnie.Preferences
import com.company.skinnie.data.response.PayloadGoogle
import com.company.skinnie.data.response.PayloadLogin
import com.company.skinnie.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()
    private val viewModelGoogle: LoginGoogleViewModel by viewModels()

    private var mIsShowPass = false
    private val RC_SIGN_IN = 1000
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

        // Configure Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        binding.apply {
            etPassword.setOnClickListener {
                mIsShowPass = !mIsShowPass
                showPassword(mIsShowPass)
            }
            tvForgotPassword.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment())
            }

            tvRegister.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }

            btnLogin.setOnClickListener {
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

            btnGoogle.setOnClickListener {
                val signInIntent = googleSignInClient.signInIntent

                binding.loading.visibility = View.VISIBLE
                startActivityForResult(signInIntent, RC_SIGN_IN)
            }
        }
        showPassword(mIsShowPass)
    }

    private fun showPassword(isShow: Boolean) {
        if (isShow) {
            binding.etPassword.transformationMethod =
                HideReturnsTransformationMethod.getInstance()
        } else {
            binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
        }
        binding.etPassword.setSelection(binding.etPassword.text.toString().length)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI
            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason
            // Please refer to the GoogleSignInStatusCodes class reference for more information
            Log.d("GoogleSignIn", "signInResult:failed code = ${e.statusCode}")
            Toast.makeText(
                requireContext(),
                "Failed to sign in. Please try again.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        // You can get the user's information from the account object
        val displayName = account?.displayName
        val email = account?.email
        val nickName = account?.givenName
        val fullName = account?.displayName
        val photoUrl = account?.photoUrl

        viewModelGoogle.userGoogle(PayloadGoogle(displayName, nickName, email))
            .observe(viewLifecycleOwner) {
                binding.loading.visibility = View.GONE
                if (it != null && it.status == "success") {
                    val preferences = Preferences(requireContext())

                    //buat nyimpan data usernamenya
                    preferences.setValues("name", nickName!!)
                    preferences.setValues("fullname", fullName!!)
                    preferences.setValues("photo", photoUrl.toString())

                    //action to activity
                    startActivity(Intent(requireContext(), NavigationActivity::class.java))
                    activity?.finish()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Failed to sign in. Please try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}