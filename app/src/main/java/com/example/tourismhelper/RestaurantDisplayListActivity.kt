package com.example.tourismhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RestaurantDisplayListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_display_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Restaurants in Sri Lanka")
        val btnTourist = findViewById<Button>(R.id.viewDetailsRes)
        btnTourist.setOnClickListener {
            val intent = Intent(this, RestaurantProfileViewActivity::class.java)
            startActivity(intent)
        }
    }
}