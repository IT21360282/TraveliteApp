package com.example.tourismhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.tourismhelper.reshotelfragment.ResHotelHomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var actionBar = getSupportActionBar()!!.hide()

        val btnResHotel = findViewById<Button>(R.id.btnRestaurantHotel)
        btnResHotel.setOnClickListener {
            Toast.makeText(this, "Restaurant/Hotel Selected", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ResHotelStartActivity::class.java)
            startActivity(intent)
        }

        val btnTourist = findViewById<Button>(R.id.btnTourist)    
        btnTourist.setOnClickListener {
            Toast.makeText(this, "Tourist Selected", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, TouristSelectActivity::class.java)
            startActivity(intent)
        }

        val btnTransport = findViewById<Button>(R.id.btnTransport)
        btnTransport.setOnClickListener {
            Toast.makeText(this, "Transport Selected", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, TransportSelectActivity::class.java)
            startActivity(intent)
        }

    }
}