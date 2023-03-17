package com.example.tourismhelper

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils

class ResHotelStartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurant_hotel_start)

        val start = supportActionBar
        start?.setTitle("Restaurant / Hotel")


        var btnLogin = findViewById<Button>(R.id.resHotelLogin)
        btnLogin.setOnClickListener {
            Toast.makeText(this, "Login Selected", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ResHotelLoginActivity::class.java)
            startActivity(intent)
        }
        var btnRegister = findViewById<Button>(R.id.resHotelRegister)
        btnRegister.setOnClickListener {
            Toast.makeText(this,"Register Selected", Toast.LENGTH_SHORT).show()
            var intent = Intent(this, ResHotelRegisterActivity::class.java)
            startActivity(intent)
        }
    }
}