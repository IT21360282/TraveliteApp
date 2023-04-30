package com.example.tourismhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HotelRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_register)

        supportActionBar?.setTitle("Hotel Register")

        var btnResStartMain = findViewById<Button>(R.id.btnSubmitHotel)
        btnResStartMain.setOnClickListener {
            var intent = Intent(this,RestaurantMainActivity::class.java)
            startActivity(intent)
        }
    }
}