package com.example.tourismhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class TransportSelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transport_select)

        val login1Button_Transport = findViewById<Button>(R.id.login1Button_Transport)
        login1Button_Transport.setOnClickListener {
            Toast.makeText(this, "Transport Selected", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, TransportLoginActivity::class.java)
            startActivity(intent)
        }

        val register1Button_Transport = findViewById<Button>(R.id.register1Button_Transport)
        register1Button_Transport.setOnClickListener {
            Toast.makeText(this, "Transport Selected", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, TransportRegisterActivity::class.java)
            startActivity(intent)
        }

    }
}