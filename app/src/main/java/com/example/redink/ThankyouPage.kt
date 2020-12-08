package com.example.redink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_thankyou_page.*

class ThankyouPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thankyou_page)

        var  message="Thank you for shopping with Us. \n You will get the Email once your order is ready for Delivery or Collection"

        tvThankyou.setText(message.toUpperCase())
        btnReturnHome.setOnClickListener{

            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}