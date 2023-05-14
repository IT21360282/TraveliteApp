package com.example.tourismhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class TransportProviderMoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transport_provider_more)

        val button_more_details_book_Transport = findViewById<Button>(R.id.button_more_details_book_Transport)
        button_more_details_book_Transport.setOnClickListener {
            Toast.makeText(this, "Transport Selected", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, TransportProviderBookActivity::class.java)
            startActivity(intent)
        }
    }
}