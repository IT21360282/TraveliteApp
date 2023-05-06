package com.example.tourismhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class TransportProviderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transport_provider)

        val button_providerMore_Transport = findViewById<Button>(R.id.button_providerMore_Transport)
        button_providerMore_Transport.setOnClickListener {
            Toast.makeText(this, "Transport Selected", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, TransportProviderMoreActivity::class.java)
            startActivity(intent)
        }
    }
}