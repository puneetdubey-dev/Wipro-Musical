package com.puneet.musicalwipro.albumsearch.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.puneet.musicalwipro.albumsearch.R

class SplashActivity : Activity() {
    private val timeout = 3000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(
            {
                val i = Intent(this, MusicAlbumActivity::class.java)
                startActivity(i)
                finish()
            }, timeout)
    }
}

