package com.example.redink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_cart_products.*

class CartProductsActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_products)

        supportActionBar?.setDisplayShowHomeEnabled(true)


        var Urlcart="https://www.roamcode.co.za/redink/retrieveTempoOrders.php?email="+UserData.Email;

        var cartProductsList=ArrayList<String>()
        var requestQ=Volley.newRequestQueue(this);
        var jsonAr=JsonArrayRequest(Request.Method.GET,Urlcart,null, { response ->

            for (jsonObj in 0.until(response.length())){

                cartProductsList.add("${"Product Name:  "+ response.getJSONObject(jsonObj).getString("ProductName")} \n ${ "Email:  "+ response.getJSONObject(jsonObj).getString("email")} \n ${  "Product Price:    R"+ response.getJSONObject(jsonObj).getString("ProductPrice")} \n ${  "Quantity Number:    "+ response.getJSONObject(jsonObj).getString("quantityNumber")} \n")
            }

            var cartProductsAdapter=ArrayAdapter(this,R.layout.history_text_orders,cartProductsList)
            listViewOrdersCart.adapter=cartProductsAdapter;


        }, { error ->

            var dialogBuilder = AlertDialog.Builder(this);
            dialogBuilder.setTitle("Error Message")
            dialogBuilder.setMessage(error.message)
            dialogBuilder.create().show()
        })
        requestQ.add(jsonAr)


        btnPayCart.setOnClickListener{


            var URlVerify="https://www.roamcode.co.za/redink/verify_order.php?email="+UserData.Email;
            var requestQ=Volley.newRequestQueue(this)
            var stringBuilder=StringRequest(Request.Method.GET,URlVerify, { response ->

              //  Toast.makeText(this,response,Toast.LENGTH_LONG).show()
                var intent= Intent(this,FinalizedShoppingActivity::class.java)
                intent.putExtra("Latest_Invoice_Number",response)
                startActivity(intent)

            }, { error ->

                var dialogBuilder = AlertDialog.Builder(this);
                dialogBuilder.setTitle("Error Message")
                dialogBuilder.setMessage(error.message)
                dialogBuilder.create().show()
            })
            requestQ.add(stringBuilder)



        }


        //retrieve the total price
        var URLCalcTotal="https://www.roamcode.co.za/redink/calculateGrand.php?email="+UserData.Email

        var requesty=Volley.newRequestQueue(this)
        var stringRequest=StringRequest(Request.Method.GET,URLCalcTotal, { response ->

            tvGrandTotal.setText("Grand Total Price R"+ response)

        }, {

            var dialogBuilder = AlertDialog.Builder(this);
            dialogBuilder.setTitle("Error Message")
            dialogBuilder.setMessage("Error detected")
            dialogBuilder.create().show()


        })

        requesty.add(stringRequest)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.continueshopping -> {

                startActivity(Intent(this, MainActivityStore::class.java))
            }

            R.id.verify -> {

                var URlVerify="https://www.roamcode.co.za/redink/verify_order.php?email="+UserData.Email;
                var requestQ=Volley.newRequestQueue(this)
                var stringBuilder=StringRequest(Request.Method.GET,URlVerify, { response ->

                    Toast.makeText(this,response,Toast.LENGTH_LONG).show()
                    var intent= Intent(this,FinalizedShoppingActivity::class.java)
                    intent.putExtra("Latest_Invoice_Number",response)
                    startActivity(intent)

                }, { error ->

                    var dialogBuilder = AlertDialog.Builder(this);
                    dialogBuilder.setTitle("Error Message")
                    dialogBuilder.setMessage(error.message)
                    dialogBuilder.create().show()
                })
                requestQ.add(stringBuilder)


            }

            R.id.decline -> {

                var UrlDelete="https://www.roamcode.co.za/redink/deleteOrders.php?email="+UserData.Email
                var requestQ=Volley.newRequestQueue(this);
                var stringRequst=StringRequest(Request.Method.GET,UrlDelete, { response ->




                    Toast.makeText(this,response,Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, MainActivityStore::class.java))


                }, { error ->
                    var dialogBuilder = AlertDialog.Builder(this);
                    dialogBuilder.setTitle("Error Message")
                    dialogBuilder.setMessage(error.message)
                    dialogBuilder.create().show()

                })
                requestQ.add(stringRequst)

            }


        }
        return super.onOptionsItemSelected(item)
    }



}

