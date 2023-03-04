package com.example.machinetestspark.signup.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.machinetestspark.R
import com.example.machinetestspark.databinding.FragmentSignupBinding
import com.example.machinetestspark.signup.domain.model.SignUpResponseModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignupFragment : Fragment(R.layout.fragment_signup) {
    private lateinit var binding: FragmentSignupBinding
    private val viewModel by viewModels<SignUpViewModel> ()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignupBinding.bind(view)
        setListeners()
        observeState()
    }



    private fun setListeners() {
        with(binding){
            signUpBtn.setOnClickListener {
                viewModel.validateSignUp(
                    email = emailId.text.toString(),
                    firstName = firstName.text.toString(),
                    lastName = lastName.text.toString(),
                    userName = userId.text.toString(),
                    password = passwordText.text.toString(),
                    password2 = passwordConfirmText.text.toString()
                )
            }
        }
    }


    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.signUpState.collect{
                    handleSignUpSuccess(it.signUpSuccess)
                }
            }
        }
    }

    private fun handleSignUpSuccess(signUpSuccess: SignUpResponseModel?) {
        if (signUpSuccess != null){
            findNavController().navigate(SignupFragmentDirections.actionSignupFragmentToLoginFragment())
        }
    }


}