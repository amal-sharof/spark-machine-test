package com.example.machinetestspark.dashboard.presentation

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.machinetestspark.R
import com.example.machinetestspark.dashboard.domain.model.DashboardResponseModel
import com.example.machinetestspark.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private lateinit var binding: FragmentDashboardBinding
    private val viewModel by viewModels<DashboardViewModel>()
    private val adapter: ImageAdapter by lazy { ImageAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardBinding.bind(view)

        observeState()
        setUpRecyclerView()
        setListeners()
        viewModel.getToken()
    }

    private fun setListeners() {
        with(binding) {
            btnLogout.setOnClickListener {
                basicAlert(btnLogout)

            }
        }

    }

    private fun setUpRecyclerView() {
        binding.recyclerView.adapter = adapter
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.dashboardState.collect {
                    handleLoading(it.loading)
                    handleDashboardSuccess(it.dashboardSuccess)
                    handleAuthToken(it.authToken)
                }
            }
        }
    }


    private fun basicAlert(view: View) {

        val builder = AlertDialog.Builder(requireContext())

        with(builder)
        {
            setTitle("Logout?")
            setMessage("Are you sure you have to logout from your account?")
            setPositiveButton("YES") { _: DialogInterface, _: Int ->
                viewModel.clearData()
                findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToLoginFragment())
            }
            setNegativeButton("CANCEL") { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
            }
            show()
        }
    }

    private fun handleAuthToken(authToken: String) {
        if (authToken.isNotBlank()) {
            viewModel.dashboard(token = authToken)
        }
    }

    private fun handleDashboardSuccess(dashboardSuccess: List<DashboardResponseModel>?) {
        if (dashboardSuccess != null) {
            adapter.submitList(dashboardSuccess)
            progressBarHide()
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