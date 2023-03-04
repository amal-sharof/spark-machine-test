package com.example.machinetestspark.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machinetestspark.app.datastore.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _isLoggedIn = Channel<Boolean>()
    val isLoggedIn = _isLoggedIn.receiveAsFlow()

    fun checkAuth() = viewModelScope.launch {
        if (dataStoreManager.getUserFromPreferencesStore().first().authToken.isBlank()) {
            _isLoggedIn.send(false)
        } else {
            _isLoggedIn.send(true)
        }
    }
}