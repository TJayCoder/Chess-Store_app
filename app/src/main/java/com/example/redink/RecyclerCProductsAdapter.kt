package com.example.redink

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cproduct_row.view.*
import kotlinx.android.synthetic.main.fragment_quantity.*


@Suppress("UNREACHABLE_CODE")
class RecyclerCProductsAdapter(var context: Context, var arrayList: ArrayList<CProducts>)  : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        val productView=LayoutInflater.from(context).inflate(R.layout.cproduct_row,parent,false)
        return ProductViewHolder(productView);
    }

    override fun getItemCount(): Int {

        return arrayList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        (holder as ProductViewHolder).initializeRowUIComponents(arrayList.get(position).productId,arrayList.get(position).productName,arrayList.get(position).prices,arrayList.get(position).images)
    }



    inner class ProductViewHolder(pview: View):RecyclerView.ViewHolder(pview){


        fun initializeRowUIComponents(productid:String, name: String, price: String, picName: String){

            itemView.tvNameCProduct.text="Product Name: "+name.toString()
            itemView.tvPriceCproduct.text="Product Price R"+price.toString()
            itemView.tvIdCProduct.text="Product Id: "+productid.toString()

            val picURL = "https://www.roamcode.co.za/redink/avaliableStock/"
            picURL.replace(" ", "%20")
            Picasso.get().load(picURL+picName).into(itemView.imageViewCProduct)

            itemView.btnAddToCartCProduct.setOnClickListener{


                UserData.ProductId=productid


                var quanityFrag=QuantityFrag()
                var fragmentManager=(itemView. context as Activity).fragmentManager
                quanityFrag.show(fragmentManager,"TAG")


            }



        }

    }


}