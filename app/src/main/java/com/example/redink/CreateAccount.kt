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
import kotlinx.android.synthetic.main.activity_create_account.*


class CreateAccount : AppCompatActivity() {
    var fullName: EditText? = null
    var email: EditText? = null
    var contact: EditText? = null
    var address: EditText? = null
    var city: EditText? = null
    var postalCode: EditText? = null
    var province: EditText? = null
    var password: EditText? = null
    var confirmPassword: EditText? = null
    var securtyq: EditText? = null
    var returnLogin: TextView? = null
    var register: Button? = null

    // Creating Progress dialog.
    var progressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        fullName = findViewById(R.id.etFullNameCreateAcc)
        email = findViewById(R.id.etEmailAccount)
        contact = findViewById(R.id.etContactCreateAcc)
        address = findViewById(R.id.etAddressCreateAcc)
        city = findViewById(R.id.etCityCreateAcc)
        postalCode = findViewById(R.id.etPostalCodeCreateAcc)
        province = findViewById(R.id.etProvinceCreateAcc)
        password = findViewById(R.id.etPasswordCreateAcc)
        confirmPassword = findViewById(R.id.etConfirmPasswordCreateAcc)
        securtyq = findViewById(R.id.etSecurity)
        register = findViewById(R.id.btnCreateAccRegister)

        userLogin()
        tvReturnLoginCreate.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this@CreateAccount,
                    LoginActivity::class.java
                )
            )
        })
    }

    fun userLogin() {
        register!!.setOnClickListener {
            val Fullname = fullName!!.text.toString().trim { it <= ' ' }
            val Email = email!!.text.toString().trim { it <= ' ' }
            val Contact = contact!!.text.toString().trim { it <= ' ' }
            val Address = address!!.text.toString().trim { it <= ' ' }
            val City = city!!.text.toString().trim { it <= ' ' }
            val PostalCode = postalCode!!.text.toString().trim { it <= ' ' }
            val Provice = province!!.text.toString().trim { it <= ' ' }
            val Password = password!!.text.toString().trim { it <= ' ' }
            val ConfirmPassword = confirmPassword!!.text.toString().trim { it <= ' ' }
            val SecurityQ = securtyq!!.text.toString().trim { it <= ' ' }
            progressDialog = ProgressDialog(this@CreateAccount)

            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            val MobilePattern = "[0-9]{10}"

            // Showing progress dialog at user registration time.
            progressDialog!!.setMessage("Please Wait, We are Inserting Your Data on Server")
            progressDialog!!.show()
            when {
                Fullname.isEmpty() -> {
                    Toast.makeText(this@CreateAccount, "Enter Full Name", Toast.LENGTH_SHORT).show()
                    // Hiding the progress dialog after all task complete.
                    progressDialog!!.dismiss()
                }
                Email.isEmpty() -> {
                    Toast.makeText(this@CreateAccount, "Enter Email", Toast.LENGTH_SHORT).show()
                    // Hiding the progress dialog after all task complete.
                    progressDialog!!.dismiss()
                }
                !Email.matches(emailPattern.toRegex()) -> {

                    Toast.makeText(this@CreateAccount, "Invalid Email Please Enter Valid Email", Toast.LENGTH_SHORT).show()
                    // Hiding the progress dialog after all task complete.
                    progressDialog!!.dismiss()

                }
                Contact.isEmpty() -> {
                    Toast.makeText(this@CreateAccount, "Enter Contact", Toast.LENGTH_SHORT).show()
                    // Hiding the progress dialog after all task complete.
                    progressDialog!!.dismiss()
                }
                !Contact.matches(MobilePattern.toRegex()) -> {
                    Toast.makeText(this@CreateAccount, "Invalid Contacts Please Enter The Valid Contact Number!!", Toast.LENGTH_SHORT).show()
                    // Hiding the progress dialog after all task complete.
                    progressDialog!!.dismiss()
                }
                Address.isEmpty() -> {
                    Toast.makeText(this@CreateAccount, "Enter Address", Toast.LENGTH_SHORT).show()
                    // Hiding the progress dialog after all task complete.
                    progressDialog!!.dismiss()
                }
                City.isEmpty() -> {
                    Toast.makeText(this@CreateAccount, "Enter City", Toast.LENGTH_SHORT).show()
                    // Hiding the progress dialog after all task complete.
                    progressDialog!!.dismiss()
                }
                PostalCode.isEmpty() -> {
                    Toast.makeText(this@CreateAccount, "Enter Postal Code", Toast.LENGTH_SHORT).show()
                    // Hiding the progress dialog after all task complete.
                    progressDialog!!.dismiss()
                }
                Provice.isEmpty() -> {
                    Toast.makeText(this@CreateAccount, "Enter Province", Toast.LENGTH_SHORT).show()
                    // Hiding the progress dialog after all task complete.
                    progressDialog!!.dismiss()
                }
                Password.isEmpty() -> {
                    Toast.makeText(this@CreateAccount, "Enter Password", Toast.LENGTH_SHORT).show()
                    // Hiding the progress dialog after all task complete.
                    progressDialog!!.dismiss()
                }
                Password.length<5 -> {

                    Toast.makeText(this@CreateAccount, "Password should contain more than five or more characters!!", Toast.LENGTH_SHORT).show()
                    // Hiding the progress dialog after all task complete.
                    progressDialog!!.dismiss()

                }
                ConfirmPassword.isEmpty() -> {
                    Toast.makeText(this@CreateAccount, "Enter Confirm Password", Toast.LENGTH_SHORT).show()
                    // Hiding the progress dialog after all task complete.
                    progressDialog!!.dismiss()
                }
                ConfirmPassword.length<5 -> {

                    Toast.makeText(this@CreateAccount, "Confirm Password should contain more than five or more characters!!", Toast.LENGTH_SHORT).show()
                    // Hiding the progress dialog after all task complete.
                    progressDialog!!.dismiss()

                }
                !Password.equals(ConfirmPassword) -> {

                    Toast.makeText(this@CreateAccount, "Passwords Do Not Match!!", Toast.LENGTH_SHORT).show()
                    // Hiding the progress dialog after all task complete.
                    progressDialog!!.dismiss()

                }
                SecurityQ.isEmpty() -> {
                    Toast.makeText(this@CreateAccount, "Enter Security Question", Toast.LENGTH_SHORT).show()
                    // Hiding the progress dialog after all task complete.
                    progressDialog!!.dismiss()
                }
                else -> {
                    val URL = "http://192.168.0.104/redink/registration.php?FullName=${Fullname.replace(" ","20%")}&Password=$Password&Email=$Email&Address=${Address.replace(" ","%20")}&CellNumber=$Contact&City=${City.replace(" ","20%")}&Provincial=$Provice&PostalCode=$PostalCode&SecurityQuestion=${SecurityQ.replace(" ","20%")}"
                    val request = Volley.newRequestQueue(this@CreateAccount)
                    val stringRequest = StringRequest(Request.Method.GET, URL, { response ->
                        if (response == "User Exist") {

                            // Hiding the progress dialog after all task complete.
                            progressDialog!!.dismiss()
                            Toast.makeText(this@CreateAccount, response, Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@CreateAccount, response, Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@CreateAccount, LoginActivity::class.java))
                        }
                    }) { error -> // Hiding the progress dialog after all task complete.
                        progressDialog!!.dismiss()
                        Toast.makeText(this@CreateAccount, error.message, Toast.LENGTH_SHORT).show()
                    }
                    request.add(stringRequest)
                }
            }
        }
    }
}

private fun String.matches(regex: String): Boolean {

    return android.util.Patterns.EMAIL_ADDRESS.matcher(regex).matches()

}
