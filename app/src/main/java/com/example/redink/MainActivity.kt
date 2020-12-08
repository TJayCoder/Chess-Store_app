package com.example.redink

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.redink.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    // Creating Progress dialog.
    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressDialog = ProgressDialog(this)

        val imageSlider: ImageSlider = findViewById(R.id.sliderImage)

        val slideModels: MutableList<SlideModel> = ArrayList<SlideModel>()

        slideModels.add(SlideModel(R.drawable.art, "Canvas"))
        slideModels.add(SlideModel(R.drawable.shirt, "Shirt"))
        slideModels.add(SlideModel(R.drawable.cup, "Cup"))
        slideModels.add(SlideModel(R.drawable.canvas, "Canvas"))


        imageSlider.setImageList(slideModels, true)

        // Showing progress dialog at user registration time.
        progressDialog!!.setMessage("Loading...")
        progressDialog!!.show()

        var CategoryList=ArrayList<CProducts>()

        val URL = "http://192.168.0.104/redink/homePage.php"
        val request = Volley.newRequestQueue(this@MainActivity)
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            URL,
            null,
            { response -> // Hiding the progress dialog after all task complete.
                progressDialog!!.dismiss()


                for (productJOIndex in 0.until(response.length())) {

                    CategoryList.add(
                        CProducts(
                            response.getJSONObject(productJOIndex).getString("idProduct"),
                            response.getJSONObject(productJOIndex).getString("ProductName"),
                            response.getJSONObject(productJOIndex).getString("ProductImage"),
                            response.getJSONObject(productJOIndex).getString("ProductPrice")
                        )
                    )

                }

                val pAdapter = RecyclerAdapterHomeStock(this, CategoryList)
                recyclerViewStock.adapter = pAdapter


            }) { error -> // Hiding the progress dialog after all task complete.
            progressDialog!!.dismiss()
            var dialogBuilder = AlertDialog.Builder(this);
            dialogBuilder.setTitle("Error Message")
            dialogBuilder.setMessage(error.message)
            dialogBuilder.create().show()
        }
        request.add(jsonArrayRequest)





    }






    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.Home -> {

                startActivity(Intent(this@MainActivity, MainActivity::class.java))
            }

            R.id.cart -> {

                startActivity(Intent(this@MainActivity, CartProductsActivity::class.java))
            }
            R.id.account -> {
                startActivity(Intent(this@MainActivity, Profile::class.java))

            }
            R.id.category -> {
                startActivity(Intent(this@MainActivity, Category::class.java))

            }
            R.id.order -> {
                startActivity(Intent(this@MainActivity, HistoryOrders::class.java))

            }
            R.id.help -> {

                startActivity(Intent(this@MainActivity, HelpUser::class.java))

            }
            R.id.about -> {

                startActivity(Intent(this@MainActivity, AboutUs::class.java))

            }

            R.id.rate -> {

                startActivity(Intent(this@MainActivity, RateApp::class.java))

            }
            R.id.logout -> {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish();
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        var images: MutableList<String>? = null
    }
}