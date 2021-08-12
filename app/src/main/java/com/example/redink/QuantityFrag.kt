package com.example.redink

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*


class QuantityFrag : android.app.DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_quantity, container, false)


        var quanityValue=fragmentView.findViewById<EditText>(R.id.etQunatity)
        var btnAddtoCart=fragmentView.findViewById<Button>(R.id.btnAddFrag)

        btnAddtoCart.setOnClickListener{

            var UrlOrder="https://www.roamcode.co.za/redink/tempOrder.php?email="+UserData.Email+"&productId="+UserData.ProductId+"&quantityNumber="+quanityValue.text.toString().trim()
            var requestQ=Volley.newRequestQueue(activity);
            var stringRequest=StringRequest(Request.Method.GET,UrlOrder, {


                Toast.makeText(activity, "Item Added Successfully",Toast.LENGTH_LONG).show()


                var intent=Intent(activity,CartProductsActivity::class.java)
                startActivity(intent)

            }, {

                var dialogBuilder = AlertDialog.Builder(activity);
                dialogBuilder.setTitle("Error Message")
                dialogBuilder.setMessage("Error Detected")
                dialogBuilder.create().show()

            })


            requestQ.add(stringRequest)



        }




        return fragmentView
    }


}