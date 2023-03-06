package com.example.machinetestspark.auth.presentation.signup

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.machinetestspark.R
import com.example.machinetestspark.auth.domain.model.signup.SignUpResponseModel
import com.example.machinetestspark.databinding.FragmentSignupBinding
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
                    handleLoading(it.loading)
                    handleSignUpSuccess(it.signUpSuccess)
                    observeError(it.error)
                }
            }
        }
    }

    private fun basicAlert() {
        val builder = AlertDialog.Builder(requireContext())
        with(builder)
        {
            setTitle("Error")
            setMessage("Make sure password is at-least 8 characters and not only numeric. \n" +
                    "Make sure email id is valid.\nIf the error occurs again, try changing your username.")
            setPositiveButton("OK") { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
            }
            show()
        }
    }

    private fun observeError(error: String) {
        if (error.isNotBlank()){
            basicAlert()
        }
    }

    private fun handleSignUpSuccess(signUpSuccess: SignUpResponseModel?) {
        if (signUpSuccess != null){
            progressBarHide()
            findNavController().navigate(SignupFragmentDirections.actionSignupFragmentToLoginFragment())
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