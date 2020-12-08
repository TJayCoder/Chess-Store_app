package com.example.redink

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_rate_app.*

class RateApp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_app)
        supportActionBar?.setDisplayShowHomeEnabled(true)



        btnRateApp.setOnClickListener{

            val rate: String = ratingBar.getRating().toString()

            Toast.makeText(this, "You Gave Us $rate Stars!!", Toast.LENGTH_SHORT).show()

        }



    }
}