@file:Suppress("DEPRECATION")

package com.example.redink

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPassword : AppCompatActivity() {
    var reset: Button? = null
    var password1: EditText? = null

    // Creating Progress dialog.
    var progressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        supportActionBar?.setDisplayShowHomeEnabled(true)

        btnSubmitReset.setOnClickListener(View.OnClickListener {

            val Pass1 = etResetPassword.text.toString().trim { it <= ' ' }
            val Pass2 = etconfirmPasswordReset.text.toString().trim { it <= ' ' }


            progressDialog = ProgressDialog(this@ResetPassword)

            // Showing progress dialog at user registration time.
            progressDialog!!.setMessage("Please Wait, We are Updating Password")
            progressDialog!!.show()


            if (Pass1.isEmpty()) {
                Toast.makeText(this@ResetPassword, "Enter Password", Toast.LENGTH_SHORT).show()
                // Hiding the progress dialog after all task complete.
                progressDialog!!.dismiss()

            }else if(Pass1.length<5) {
                android.widget.Toast.makeText(
                    this@ResetPassword,
                    "Password Must Contain Five Or More character",
                    android.widget.Toast.LENGTH_SHORT
                ).show()
                // Hiding the progress dialog after all task complete.
                progressDialog!!.dismiss()

            } else if(Pass2.isEmpty()){

                Toast.makeText(this@ResetPassword, "Enter Confirm Password", Toast.LENGTH_SHORT).show()
                // Hiding the progress dialog after all task complete.
                progressDialog!!.dismiss()
            }else if(!Pass1.equals(Pass2)){

                Toast.makeText(this@ResetPassword, "Password Do Not Match", Toast.LENGTH_SHORT).show()
                // Hiding the progress dialog after all task complete.
                progressDialog!!.dismiss()

            }
            else {
                val Email = intent.getStringExtra("Email")
                val URL =
                    "http://192.168.0.104/redink/resetPassword.php?Email=$Email&Password=$Pass1"
                val request = Volley.newRequestQueue(this@ResetPassword)
                val stringRequest = StringRequest(Request.Method.GET, URL, { response ->
                    if (response == "User does not exist") {

                        // Hiding the progress dialog after all task complete.
                        progressDialog!!.dismiss()
                        Toast.makeText(this@ResetPassword, response, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@ResetPassword, response, Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@ResetPassword, LoginActivity::class.java))
                    }
                }) { error -> // Hiding the progress dialog after all task complete.
                    progressDialog!!.dismiss()
                    Toast.makeText(this@ResetPassword, error.message, Toast.LENGTH_SHORT).show()
                }
                request.add(stringRequest)
            }
        })
    }
}