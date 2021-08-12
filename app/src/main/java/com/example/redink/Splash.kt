package com.example.redink

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val intent = Intent(this@Splash, LoginActivityStore::class.java)
            startActivity(intent)
            finish()
        }, time.toLong())
    }

    companion object {
        private const val time = 4000
    }
}