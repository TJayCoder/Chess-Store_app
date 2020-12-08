package com.example.redink

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_profile.*
import org.json.JSONException
import org.json.JSONObject

class Profile : AppCompatActivity() {

    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        progressDialog = ProgressDialog(this)

        // Showing progress dialog at user registration time.
        progressDialog!!.setMessage("Loading...")
        progressDialog!!.show()





            val URL = "http://192.168.0.104/redink/UserAccount.php?Email="+UserData.Email
            val request = Volley.newRequestQueue(this)
            val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, URL, null, { response -> // Hiding the progress dialog after all task complete.
                progressDialog!!.dismiss()
                var i = 0
                i = 0
                while (i < response.length()) {
                    try {
                        val person = response[i] as JSONObject
                        val Email = person.getString("Email")
                        val Contact = person.getString("CellNumber")
                        val Address = person.getString("Address")
                        val City = person.getString("City")
                        val Province = person.getString("Province")
                        val PostalCode = person.getString("PostalCode")


                        etEmailAccount.setText(Email)
                        etContactNumberAccount.setText(Contact)
                        etAddressAccount.setText(Address)
                        etCityAccount.setText(City)
                        etProvinceAccount.setText(Province)
                        etPostalCodeAccount.setText(PostalCode)



                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    i++
                }
            }) { error -> // Hiding the progress dialog after all task complete.
                progressDialog!!.dismiss()
                Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
            }
            request.add(jsonArrayRequest)




        btnupdateAccount.setOnClickListener{

            val MobilePattern = "[0-9]{10}"

            val Contact = etContactNumberAccount!!.text.toString().trim { it <= ' ' }
            val Address = etAddressAccount!!.text.toString().trim { it <= ' ' }
            Address.replace(" ","20%")
            val City = etCityAccount!!.text.toString().trim { it <= ' ' }
            val PostalCode = etPostalCodeAccount!!.text.toString().trim { it <= ' ' }
            val Provice = etProvinceAccount!!.text.toString().trim { it <= ' ' }

            if(!Contact.matches(MobilePattern.toRegex()))
                {
            Toast.makeText(this, "Invalid Contacts Please Enter The Valid Contact Number!!", Toast.LENGTH_SHORT).show()
            // Hiding the progress dialog after all task complete.
            progressDialog!!.dismiss()
             }else{

            var URLupdate:String= "http://192.168.0.104/redink/updateUserInfor.php?Address=${Address.replace(" ","%20")}&CellNumber=$Contact&City=${City.replace(" ","20%")}&Province=$Provice&PostalCode=$PostalCode&Email=${UserData.Email}"


            var requesty=Volley.newRequestQueue(this);
            var stringrequest=StringRequest(Request.Method.GET,URLupdate, { response ->

                progressDialog!!.dismiss()

                Toast.makeText(this,response,Toast.LENGTH_LONG).show()


                startActivity(Intent(this, MainActivity::class.java))


            }, { error ->

                progressDialog!!.dismiss()
                var dialogBuilder = AlertDialog.Builder(this);
                dialogBuilder.setTitle("Error Message")
                dialogBuilder.setMessage(error.message)
                dialogBuilder.create().show()
            })
            requesty.add(stringrequest)

        }
        }



    }



}

private fun String.matches(regex: Regex, function: () -> Unit): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(regex.toString()).matches()

}
