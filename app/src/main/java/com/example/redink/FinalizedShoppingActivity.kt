package com.example.redink

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.paypal.android.sdk.payments.*
import kotlinx.android.synthetic.main.activity_finalized_shopping.*
import java.math.BigDecimal

class FinalizedShoppingActivity : AppCompatActivity() {

    var ttPrice:Long=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finalized_shopping)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        tvbankdetails.setText(
            "You can Pay at Standard Bank: \n\n\n" +
                    "Account No: 3487837487 \n" +
                    "Reference: Email + Invoice Number\n" +
                    "Brantch: 38439 \n")

        //retrieve the total price
        var URLCalcTotal="http://192.168.0.104/redink/CalculateTotalPriceOrder.php?email="+UserData.Email

        var requestQ=Volley.newRequestQueue(this)
        var stringRequest=StringRequest(Request.Method.GET,URLCalcTotal, { response ->

            btnPayFinal.text="Pay R"+ response+ " Via PayPal now!"
            ttPrice=response.toLong()

        }, {

            var dialogBuilder = AlertDialog.Builder(this);
            dialogBuilder.setTitle("Error Message")
            dialogBuilder.setMessage("Error detected")
            dialogBuilder.create().show()


        })

        requestQ.add(stringRequest)

        //calling environment on paypal configuration
        var paypalConfig:PayPalConfiguration=PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(MyPayPal.clientID)

        //paypal service
        var ppService=Intent(this,PayPalService::class.java)

        //send pay pal config to paypal config
        ppService.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,paypalConfig)

        //startService
        startService(ppService)

        btnPayFinal.setOnClickListener{


            //process the payment
            var ppProcessing=PayPalPayment(BigDecimal.valueOf(ttPrice),"USD","RedInk Media Store",PayPalPayment.PAYMENT_INTENT_SALE)

            var paypalPaymentIntent=Intent(this,PaymentActivity::class.java)
            paypalPaymentIntent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,paypalConfig)
            paypalPaymentIntent.putExtra(PaymentActivity.EXTRA_PAYMENT,ppProcessing)
            startActivityForResult(paypalPaymentIntent,1000)


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==1000){

           if (resultCode==Activity.RESULT_OK){
               startActivity(Intent (this,ThankyouPage::class.java))
           }else{

               Toast.makeText(this, "Something went wrong Payment Failed. Please Try Again", Toast.LENGTH_SHORT).show()

           }

        }

    }

    override fun onDestroy() {
        super.onDestroy()

        stopService(Intent(this,PayPalService::class.java))


    }

}