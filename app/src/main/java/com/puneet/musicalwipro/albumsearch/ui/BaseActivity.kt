package com.puneet.musicalwipro.albumsearch.ui

import android.annotation.SuppressLint
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.puneet.musicalwipro.albumsearch.utils.internet.ConnectivityReceiver

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {
    private var mSnackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(ConnectivityReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }


    private fun showMessage(isConnected: Boolean) {
        if (!isConnected) {
            val messageToUser = "You are offline now." //TODO

            mSnackBar = Snackbar.make(getWindow().getDecorView().getRootView(), messageToUser, Snackbar.LENGTH_INDEFINITE)
            mSnackBar?.show()
        } else {
            mSnackBar?.dismiss()
        }
    }

    override fun onResume() {
        super.onResume()

        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(ConnectivityReceiver()
        )
    }

    /**
     * Callback will be called when there is change
     */
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showMessage(isConnected)
    }
}