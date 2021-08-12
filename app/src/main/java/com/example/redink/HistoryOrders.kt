package com.example.redink

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_cart_products.*
import kotlinx.android.synthetic.main.activity_history_orders.*
import kotlinx.android.synthetic.main.activity_profile.*
import org.json.JSONException
import org.json.JSONObject

class HistoryOrders : AppCompatActivity() {

    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_orders)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        progressDialog = ProgressDialog(this)

        // Showing progress dialog at user registration time.
        progressDialog!!.setMessage("Loading...")
        progressDialog!!.show()

        var Invoices=ArrayList<String>()

        val URL = "https://www.roamcode.co.za/redink/orderHistory.php?Email="+UserData.Email
        val request = Volley.newRequestQueue(this)
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, URL, null, { response -> // Hiding the progress dialog after all task complete.
            progressDialog!!.dismiss()

            for (jsonObj in 0.until(response.length())){

                Invoices.add("${"Invoice No:    " +response.getJSONObject(jsonObj).getString("invoice_num")}\n ${"Invoice Appointment Time:  "+ response.getJSONObject(jsonObj).getString("invoice_appointment_time")} \n ${ "Email:  "+ response.getJSONObject(jsonObj).getString("email")} ")
            }

            var invoiceAdapter= ArrayAdapter(this,R.layout.history_text_orders,Invoices)
            listInvoices.adapter=invoiceAdapter;



        }) { error -> // Hiding the progress dialog after all task complete.
            progressDialog!!.dismiss()
            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
        }
        request.add(jsonArrayRequest)


    }


}