package com.example.tourismhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RestaurantProfileViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_profile_view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Restaurant Name")
    }
}