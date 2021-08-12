package com.example.redink

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivityStore : AppCompatActivity() {

    // Creating Progress dialog.
    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressDialog = ProgressDialog(this)

        val imageSlider: ImageSlider = findViewById(R.id.sliderImage)

        val slideModels: MutableList<SlideModel> = ArrayList<SlideModel>()

        slideModels.add(SlideModel(R.drawable.chessboard, "Chessboard"))
        slideModels.add(SlideModel(R.drawable.specialbottle, "bottle"))
        slideModels.add(SlideModel(R.drawable.chessset, "Chess-Set"))
        slideModels.add(SlideModel(R.drawable.scorebooks, "Books"))


        imageSlider.setImageList(slideModels, true)

        // Showing progress dialog at user registration time.
        progressDialog!!.setMessage("Loading...")
        progressDialog!!.show()

        var CategoryList=ArrayList<CProducts>()

        val URL = "https://www.roamcode.co.za/redink/homePage.php"
        val request = Volley.newRequestQueue(this@MainActivityStore)
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

                startActivity(Intent(this@MainActivityStore, MainActivityStore::class.java))
            }

            R.id.cart -> {

                startActivity(Intent(this@MainActivityStore, CartProductsActivity::class.java))
            }
            R.id.account -> {
                startActivity(Intent(this@MainActivityStore, Profile::class.java))

            }
            R.id.category -> {
                startActivity(Intent(this@MainActivityStore, Category::class.java))

            }
            R.id.order -> {
                startActivity(Intent(this@MainActivityStore, HistoryOrders::class.java))

            }
            R.id.help -> {

                startActivity(Intent(this@MainActivityStore, HelpUser::class.java))

            }
            R.id.about -> {

                startActivity(Intent(this@MainActivityStore, AboutUs::class.java))

            }

            R.id.rate -> {

                startActivity(Intent(this@MainActivityStore, RateApp::class.java))

            }
            R.id.logout -> {
                startActivity(Intent(this@MainActivityStore, LoginActivityStore::class.java))
                finish();
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        var images: MutableList<String>? = null
    }
}