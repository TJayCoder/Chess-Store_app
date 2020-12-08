package com.example.redink

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_about_us.*

class AboutUs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        supportActionBar?.setDisplayShowHomeEnabled(true)

        tvCopyright.setText("Copyright C 1984-2020 Redink and its licensors. All rights reserved. Redink, the Redink logo, and Redink-Media are either registered or" +
                " trademarks of Redink in South Africa and other countries. All other trademarks are the property of their respective owner ")


    }
}