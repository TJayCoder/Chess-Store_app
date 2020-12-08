package com.example.redink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_category.*

class Category : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        var categoryUrl="http://192.168.0.104/redink/collectionProduct.php";

        var CategoryList=ArrayList<String>();

        var requestQ=Volley.newRequestQueue(this);
        var jsonAr=JsonArrayRequest(Request.Method.GET,categoryUrl,null, { response ->



            for(jsonObject in 0.until(response.length())) {

                CategoryList.add(response.getJSONObject (jsonObject).getString("ProductCategory"))

            }

            var categoryListAdapter=ArrayAdapter(this,R.layout.category_text_style,CategoryList)
            listViewCategory.adapter=categoryListAdapter



        }, { error ->

            var dialogBuilder =AlertDialog.Builder(this);
            dialogBuilder.setTitle("Error Message")
            dialogBuilder.setMessage(error.message)
            dialogBuilder.create().show()


        })

        requestQ.add(jsonAr)

        listViewCategory.setOnItemClickListener { parent, view, position, id ->


            var tappedCategory=CategoryList.get(position)
            var intent=Intent(this,FetchCProductActivity::class.java)
            intent.putExtra("CATEGORY",tappedCategory)
            startActivity(intent)

        }




    }
}