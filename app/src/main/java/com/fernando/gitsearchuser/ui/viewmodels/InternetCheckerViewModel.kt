package com.fernando.gitsearchuser.ui.viewmodels

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InternetCheckerViewModel(connectivityManager: ConnectivityManager) : ViewModel() {
    private val _isConnected: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isConnected: Flow<Boolean>
        get() = _isConnected.asStateFlow()

    init {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        val networkCallback = object : ConnectivityManager.NetworkCallback() {

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                val connected = networkCapabilities.hasCapability(
                    NetworkCapabilities.NET_CAPABILITY_VALIDATED
                )

                _isConnected.update { connected }
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                _isConnected.update { false }
            }

            override fun onUnavailable() {
                super.onUnavailable()
                _isConnected.update { false }
            }
        }

        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }

    class Factory(
        private val connectivityManager: ConnectivityManager
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(InternetCheckerViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return InternetCheckerViewModel(connectivityManager) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}