package com.example.redink

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_fetch_c_product.*

class FetchCProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_c_product)

        supportActionBar?.setDisplayShowHomeEnabled(true)

        val itemsclicked: String = intent.getStringExtra("CATEGORY")

        var CategoryList=ArrayList<CProducts>()

        var URLCategory =
            "https://www.roamcode.co.za/redink/fetchProduct.php?ProductCategory=" + itemsclicked.replace(" ","%20");
        val requestQ=Volley.newRequestQueue(this)
        val jsonAR=JsonArrayRequest(Request.Method.GET,URLCategory,null, { response ->


            for(productJOIndex in 0.until(response.length())){

                CategoryList.add(CProducts(
                    response.getJSONObject(productJOIndex).getString("idProduct"),
                    response.getJSONObject(productJOIndex).getString("ProductName"),
                    response.getJSONObject(productJOIndex).getString("ProductImage"),
                    response.getJSONObject(productJOIndex).getString("ProductPrice")
                ))

            }

            val pAdapter=RecyclerCProductsAdapter(this,CategoryList)
            RecyclerViewAllProducts.layoutManager=LinearLayoutManager(this)
            RecyclerViewAllProducts.adapter=pAdapter


        }, { error ->

            var dialogBuilder = AlertDialog.Builder(this);
            dialogBuilder.setTitle("Error Message")
            dialogBuilder.setMessage(error.message)
            dialogBuilder.create().show()
        })

        requestQ.add(jsonAR);
    }

}
