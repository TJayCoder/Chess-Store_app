package com.example.redink

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_about_us.*

class AboutUs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        supportActionBar?.setDisplayShowHomeEnabled(true)

        tvCopyright.setText("Copyright C 1984-2021 Chess Store and its licensors. All rights reserved. Chess Store, the Chess Store logo, and Redink-Media are either registered or" +
                " trademarks of Chess Store in South Africa and other countries. All other trademarks are the property of their respective owner. ")


    }
}