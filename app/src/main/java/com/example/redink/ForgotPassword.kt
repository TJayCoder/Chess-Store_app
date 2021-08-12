@file:Suppress("DEPRECATION")

package com.example.redink

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPassword : AppCompatActivity() {
    var returnLogin: TextView? = null
    var email: EditText? = null
    var securityq: EditText? = null
    var proceed: Button? = null

    // Creating Progress dialog.
    var progressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        supportActionBar?.setDisplayShowHomeEnabled(true)


        btnPassProceed.setOnClickListener(View.OnClickListener {
            val Email = etEmailPass.getText().toString().trim { it <= ' ' }
            val SecurityQuestion = atSecurityQPass.getText().toString().trim { it <= ' ' }

            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"


            progressDialog = ProgressDialog(this@ForgotPassword)
            // Showing progress dialog at user registration time.
            progressDialog!!.setMessage("Please Wait, Still loading")
            progressDialog!!.show()
            when {
                Email.isEmpty() -> {
                    Toast.makeText(this@ForgotPassword, "Enter Email", Toast.LENGTH_SHORT).show()
                    // Hiding the progress dialog after all task complete.
                    progressDialog!!.dismiss()

                }
                !Email.matches(emailPattern.toRegex()) -> {

                    Toast.makeText(
                        this@ForgotPassword,
                        "Invalid Email Please Enter Valid Email",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Hiding the progress dialog after all task complete.
                    progressDialog!!.dismiss()

                }
                SecurityQuestion.isEmpty() -> {
                    Toast.makeText(
                        this@ForgotPassword,
                        "Answer Security Question",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Hiding the progress dialog after all task complete.
                    progressDialog!!.dismiss()
                }
                else -> {
                    val URL =
                        "https://www.roamcode.co.za/redink/forgotPassword.php?Email=$Email&SecurityQuestion=$SecurityQuestion"
                    val request = Volley.newRequestQueue(this@ForgotPassword)
                    val stringRequest = StringRequest(Request.Method.GET, URL, { response ->
                        if (response == "Correct Security details") {

                            // Hiding the progress dialog after all task complete.
                            progressDialog!!.dismiss()
                            Toast.makeText(this@ForgotPassword, response, Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@ForgotPassword, ResetPassword::class.java)
                            intent.putExtra("Email", Email)
                            startActivity(intent)
                        } else {
                            // Hiding the progress dialog after all task complete.
                            progressDialog!!.dismiss()
                            Toast.makeText(this@ForgotPassword, response, Toast.LENGTH_SHORT).show()
                        }
                    }) { error -> // Hiding the progress dialog after all task complete.
                        progressDialog!!.dismiss()
                        Toast.makeText(this@ForgotPassword, error.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                    request.add(stringRequest)
                }
            }
        })
        tvReturnLoginPassword.setOnClickListener(View.OnClickListener { startActivity(Intent(this@ForgotPassword, LoginActivityStore::class.java)) })
    }
}