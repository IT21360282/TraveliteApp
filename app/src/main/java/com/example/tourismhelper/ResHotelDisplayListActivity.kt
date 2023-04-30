package com.example.tourismhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ResHotelDisplayListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_res_hotel_display_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Hotels in Sri Lanka")

        var btnView = findViewById<Button>(R.id.viewDetails)
        btnView.setOnClickListener {
            var intent = Intent(this, HotelProfileViewActivity::class.java)
            startActivity(intent)
        }
    }
}