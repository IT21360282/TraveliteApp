package com.example.tourismhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ResHotelRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_res_hotel_register)

        var register = supportActionBar
        register?.setTitle("Restaurant/Hotel Register")

        val nextReg = findViewById<Button>(R.id.btnNextRegResHotel)
        nextReg.setOnClickListener {
            var intent = Intent(this, HotelRegisterActivity::class.java)
            startActivity(intent)
        }
    }
}