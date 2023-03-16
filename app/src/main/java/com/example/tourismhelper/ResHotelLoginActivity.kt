package com.example.tourismhelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ResHotelLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurant_hotel_login)

        var login = supportActionBar
        login?.setTitle("Restaurant/Hotel Login")

        var btnLogin = findViewById<Button>(R.id.btnResHotelRegSubmit)
        btnLogin.setOnClickListener {
            Toast.makeText(this, "Back to Restaurant/Hotel", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ResHotelStartActivity::class.java)
            startActivity(intent)
        }

    }
}