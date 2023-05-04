package com.example.tourismhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tourismhelper.transportfragment.TransportHomeFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TransportLoginActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transport_login)

        var registerTravel = supportActionBar
        registerTravel?.setTitle("Transport Login")

        var vLoginRegNum = findViewById<EditText>(R.id.vehicleOwnerUserName_Transport)
        var password = findViewById<EditText>(R.id.vehicleOwnerPassword_Transport)

        val login2Button_Transport = findViewById<Button>(R.id.login2Button_Transport)
        login2Button_Transport.setOnClickListener {
            /*Toast.makeText(this, "Transport Selected", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, TransportMainActivity::class.java)
            startActivity(intent)*/

            databaseReference = FirebaseDatabase.getInstance().getReference("transport_provider")
            databaseReference.child(vLoginRegNum.text.toString()).get().addOnSuccessListener {
                if(it.exists()){
                    var passwordFromDB = it.child("password").value.toString()
                    if(password.text.toString() == passwordFromDB){
                        Toast.makeText(this, "Logged In Successfully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, TransportMainActivity::class.java)
                        startActivity(intent)
                        vLoginRegNum.text.clear()
                        password.text.clear()
                    }
                    else{
                        Toast.makeText(this, "Register Number or Password is Incorrect", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this, "User Does Not Exist", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}