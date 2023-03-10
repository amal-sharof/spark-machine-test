package com.example.machinetestspark.auth.presentation.siginin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.machinetestspark.R
import com.example.machinetestspark.app.datastore.UserDetails
import com.example.machinetestspark.databinding.FragmentLoginBinding
import com.example.machinetestspark.auth.domain.model.signin.LoginResponseModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        setListeners()
        observeState()
    }

    private fun setListeners() {
        with(binding){
            logInBtn.setOnClickListener {
                viewModel.validateLogin(
                    userName = userId.text.toString(),
                    password = passwordText.text.toString()
                )
            }
            signUpBtn.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignupFragment())
            }
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.loginState.collect{
                    handleLoading(it.loading)
                    handleLoginSuccess(it.loginSuccess)
                    observeError(it.error)
                }
            }
        }
    }

    private fun observeError(error: String) {
        if (error.isNotBlank()){
            Toast.makeText(requireContext(),error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleLoginSuccess(loginSuccess: LoginResponseModel?) {
        if (loginSuccess != null){
            progressBarHide()
            val userData = UserDetails(
                authToken = loginSuccess.authToken,
                email = loginSuccess.email,
                userId = loginSuccess.userName
            )
            viewModel.saveUserData(userData)
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToDashboardFragment())
        }
    }

    private fun handleLoading(flag: Boolean) {
        binding.loadingProgressBar.isVisible = flag
    }

    private fun progressBarHide() {
        if (binding.loadingProgressBar.isVisible) {
            binding.loadingProgressBar.visibility = View.GONE
        }
    }
}