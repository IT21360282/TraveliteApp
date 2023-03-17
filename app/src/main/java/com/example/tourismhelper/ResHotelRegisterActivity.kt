package com.example.tourismhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ResHotelRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_res_hotel_register)

        var register = supportActionBar
        register?.setTitle("Restaurant/Hotel Register")
    }
}