package com.example.redink

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cproduct_row.view.*
import kotlinx.android.synthetic.main.row_item.*
import kotlinx.android.synthetic.main.row_item.view.*

@Suppress("UNREACHABLE_CODE")
class RecyclerAdapterHomeStock(var context: Context, var products: ArrayList<CProducts>)  : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val productView=LayoutInflater.from(context).inflate(R.layout.row_item,parent,false)
        return ProductViewHolder(productView);
    }

    //put the data inside view
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as RecyclerAdapterHomeStock.ProductViewHolder).initializeRowUIComponents(products.get(position).productId,products.get(position).productName,products.get(position).prices,products.get(position).images)

    }

    // represent number of rows in a recycler view
    override fun getItemCount(): Int {
        return products.size
    }

    inner class ProductViewHolder(pview: View):RecyclerView.ViewHolder(pview){


        fun initializeRowUIComponents(productid:String, name: String, price: String, picName: String){

            //initialize

            itemView.tvPName.text="Product Name: "+name.toString().trim()
            itemView.tvPrice.text="Product Price R"+price.toString().trim()

            val picURL = "https://www.roamcode.co.za/redink/avaliableStock/"
            picURL.replace(" ", "%20")
            Picasso.get().load(picURL+picName).into(itemView.ivProduct)


            itemView.btnCart.setOnClickListener{

                UserData.ProductId=productid


                var quanityFrag=QuantityFrag()
                var fragmentManager=(itemView. context as Activity).fragmentManager
                quanityFrag.show(fragmentManager,"TAG")



            }

        }
    }
}