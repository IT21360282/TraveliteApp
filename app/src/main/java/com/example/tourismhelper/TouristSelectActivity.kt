package com.example.tourismhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.tourismhelper.tourfragment.TouristLocationFragment
import com.example.tourismhelper.tourfragment.TouristReviewFragment

class TouristSelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tourist_select)

        val btnTouristlog = findViewById<Button>(R.id.btnTouristlog)
        btnTouristlog.setOnClickListener {
            Toast.makeText(this, "Login Selected", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, TouristLoginActivity::class.java)
            startActivity(intent)
        }

        val btnTouristreg = findViewById<Button>(R.id.btnTouristreg)
        btnTouristreg.setOnClickListener {
            Toast.makeText(this, "Login Selected", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, TouristRegisterActivity::class.java)
            startActivity(intent)
        }

        val btnTouristGuest = findViewById<Button>(R.id.btnTouristGuest)
        btnTouristGuest.setOnClickListener {
            Toast.makeText(this, "Login Selected", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, TouristHomeActivity::class.java)
            startActivity(intent)
        }

        val btnToursitDeact = findViewById<Button>(R.id.btnToursitDeact)
        btnToursitDeact.setOnClickListener {
            Toast.makeText(this, "Login Selected", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, TouristDeactivationActivity::class.java)
            startActivity(intent)
        }

        val btnLocation = findViewById<Button>(R.id.btnLocation)
        btnLocation.setOnClickListener {
            Toast.makeText(this, "Login Selected", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, TouristLocationFragment::class.java)
            startActivity(intent)
        }

        val btncomment = findViewById<Button>(R.id.btncomment)
        btncomment.setOnClickListener {
            Toast.makeText(this, "Login Selected", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, TouristReviewFragment::class.java)
            startActivity(intent)
        }








    }

}