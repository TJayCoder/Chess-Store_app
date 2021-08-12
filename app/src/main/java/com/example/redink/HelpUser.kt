package com.example.redink

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_help_user.*

class HelpUser : AppCompatActivity() {
    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help_user)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        progressDialog = ProgressDialog(this)


        btnSubmitContact.setOnClickListener(View.OnClickListener {

            val Contact: String = etContactNumberContact.text.toString().trim { it <= ' ' }
            val OrderNumber: String = etOrderNumberContact.text.toString().trim { it <= ' ' }
            val Comment: String = etCommentContact.text.toString().trim { it <= ' ' }



            // Showing progress dialog at user registration time.
            progressDialog!!.setMessage("Please Wait, We are Inserting Your Data on Server")
            progressDialog!!.show()


            if (Contact.isEmpty()) {
                Toast.makeText(this, "Enter Contact", Toast.LENGTH_SHORT).show()
                // Hiding the progress dialog after all task complete.
                progressDialog!!.dismiss()
            } else if (OrderNumber.isEmpty()) {
                Toast.makeText(this, "Enter Order Number", Toast.LENGTH_SHORT).show()
                // Hiding the progress dialog after all task complete.
                progressDialog!!.dismiss()
            } else if (Comment.isEmpty()) {
                Toast.makeText(this, "Enter Complain/Comments", Toast.LENGTH_SHORT).show()
            } else {
                val URLHelp = "https://www.roamcode.co.za/redink/contact.php?OrderNumber="+OrderNumber+"&Email="+UserData.Email+"&CellNumber="+Contact+"&Message="+Comment
                val request = Volley.newRequestQueue(this)
                val stringRequest = StringRequest(
                    Request.Method.GET, URLHelp,
                    { response ->
                        if (response == "User Exist") {

                            // Hiding the progress dialog after all task complete.
                            progressDialog!!.dismiss()
                            Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivityStore::class.java))
                        }
                    }) { error -> // Hiding the progress dialog after all task complete.
                    progressDialog!!.dismiss()

                }
                request.add(stringRequest)
            }
        })
    }
}