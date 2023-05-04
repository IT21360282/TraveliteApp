package com.example.tourismhelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ResHotelLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurant_hotel_login)

        var login = supportActionBar
        login?.setTitle("Restaurant/Hotel Login")

        var UNorEmail = findViewById<EditText>(R.id.edtLoginUNorEmail)
        var password = findViewById<EditText>(R.id.edtLoginPass)
        var LoginToReg = findViewById<TextView>(R.id.txtViewLoginToReg)
        var btnLogin = findViewById<Button>(R.id.btnSubmitLogin)

        btnLogin.setOnClickListener{
            validate(UNorEmail.text.toString(), password.text.toString())
            password.text = null
        }

        LoginToReg.setOnClickListener {
            Toast.makeText(this, "User Registration", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ResHotelRegisterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun validate(user:String, password:String) {
        var un = "nilanka"
        var pass = "SN1234"
        var bType = "hotel"
        if(un == user && pass == password){
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
        }
    }
}