package com.example.tourismhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.tourismhelper.tourfragment.TouristLocationFragment

class TouristLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tourist_login)

        val btntouristlogin = findViewById<Button>(R.id.btntouristlogin)
        btntouristlogin.setOnClickListener {
            Toast.makeText(this, "Login Selected", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, TouristMainActivity::class.java)
            startActivity(intent)
        }
    }

}