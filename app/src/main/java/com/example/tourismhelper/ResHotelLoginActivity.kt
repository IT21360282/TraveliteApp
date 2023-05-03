package com.example.tourismhelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.values

class ResHotelLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurant_hotel_login)

        var login = supportActionBar
        login?.setTitle("Restaurant/Hotel Login")

        var UNorEmail = findViewById<EditText>(R.id.edtLoginUNorEmail)
        var UNorEmailText = UNorEmail.text.toString()
        var password = findViewById<EditText>(R.id.edtLoginPass)
        var LoginToReg = findViewById<TextView>(R.id.txtViewLoginToReg)
        var btnLogin = findViewById<Button>(R.id.btnSubmitLogin)

        btnLogin.setOnClickListener{
            Toast.makeText(this, "${UNorEmail.text.toString()}", Toast.LENGTH_SHORT).show()
        }

        LoginToReg.setOnClickListener {
            Toast.makeText(this, "User Registration", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ResHotelRegisterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun validate(userTxt:String, passwordTxt:String) {
        val database = FirebaseDatabase.getInstance().getReference("hotel_restaurant_user")

        val userId = "8HPEWixAiKVMSpVI57iV" // Replace with the key of the child node you want to retrieve.

        database.child(userId).child("fullName").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val name = dataSnapshot.getValue(String::class.java)
                if (name != null) {
                    // Display the name in a Toast message.
                    Toast.makeText(applicationContext, "User name: $name", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // This method will be called if there is an error reading the data from the database.
            }
        })



        /*var un = "nilanka"
        var pass = "SN1234"
        var bType = "restaurant"
        if(un == userTxt && pass == passwordTxt){
            if(bType == "hotel"){
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ResHotelMainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, RestaurantMainActivity::class.java)
                startActivity(intent)
            }
        }
        else{
            Toast.makeText(this, "Incorrect UserName or Password", Toast.LENGTH_SHORT).show()
        }*/

    }
}