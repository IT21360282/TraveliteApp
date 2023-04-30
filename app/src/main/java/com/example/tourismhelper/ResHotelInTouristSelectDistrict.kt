package com.example.tourismhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ResHotelInTouristSelectDistrict : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_res_hotel_in_tourist_select_district)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Hotels in Sri Lanka")
    }
}