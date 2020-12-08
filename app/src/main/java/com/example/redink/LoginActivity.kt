@file:Suppress("DEPRECATION")

package com.example.redink

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.redink.LoginActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var login: Button? = null
    var forgotpassword: Button? = null
    var createAccount: Button? = null
    var email: EditText? = null
    var password: EditText? = null

    // Creating Progress dialog.
    var progressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.etEmailAccount)
        password = findViewById(R.id.etPasswordLogin)
        login = findViewById(R.id.btnLogin)
        login()
        password()
        userAccount()
    }

    fun login() {
        login!!.setOnClickListener {


            val Email = email!!.text.toString().trim { it <= ' ' }
            val Password = password!!.text.toString().trim { it <= ' ' }


            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

            progressDialog = ProgressDialog(this@LoginActivity)
            // Showing progress dialog at user registration time.
            progressDialog!!.setMessage("Please Wait, Still loading")
            progressDialog!!.show()
            when {
                Email.isEmpty() -> {
                    Toast.makeText(this@LoginActivity, "Enter Email", Toast.LENGTH_SHORT).show()
                    // Hiding the progress dialog after all task complete.
                    progressDialog!!.dismiss()
                }
                !Email.matches(emailPattern.toRegex()) -> {

                Toast.makeText(
                    this@LoginActivity,
                    "Invalid Email Please Enter Valid Email",
                    Toast.LENGTH_SHORT
                ).show()
                // Hiding the progress dialog after all task complete.
                progressDialog!!.dismiss()

            }
                Password.isEmpty() -> {
                    Toast.makeText(this@LoginActivity, "Enter Password", Toast.LENGTH_SHORT).show()
                    // Hiding the progress dialog after all task complete.
                    progressDialog!!.dismiss()
                }
                else -> {
                    val URL = "http://192.168.0.104/redink/login.php?Email=$Email&Password=$Password"
                    val request = Volley.newRequestQueue(this@LoginActivity)
                    val stringRequest = StringRequest(Request.Method.GET, URL, { response ->
                        if (response == "Login Successful") {

                            // Hiding the progress dialog after all task complete.
                            progressDialog!!.dismiss()
                            Toast.makeText(this@LoginActivity, response, Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)

                            //passing the value of the user email
                            UserData.Email= email!!.text.toString().trim();

                            startActivity(intent)
                            finish()
                        } else if (response == "login Failed") {
                            // Hiding the progress dialog after all task complete.
                            progressDialog!!.dismiss()
                            Toast.makeText(this@LoginActivity, response, Toast.LENGTH_SHORT).show()
                        } else {
                            // Hiding the progress dialog after all task complete.
                            progressDialog!!.dismiss()
                            Toast.makeText(this@LoginActivity, response, Toast.LENGTH_SHORT).show()
                        }
                    }) { error -> // Hiding the progress dialog after all task complete.
                        progressDialog!!.dismiss()
                        Toast.makeText(this@LoginActivity, error.message, Toast.LENGTH_SHORT).show()
                    }
                    request.add(stringRequest)
                }
            }
        }
    }

    fun password() {
        btnforgotpass1!!.setOnClickListener { startActivity(Intent(this@LoginActivity, ForgotPassword::class.java)) }
    }

    fun userAccount() {
        btnCreateAcc!!.setOnClickListener { startActivity(Intent(this@LoginActivity, CreateAccount::class.java)) }
    }
}