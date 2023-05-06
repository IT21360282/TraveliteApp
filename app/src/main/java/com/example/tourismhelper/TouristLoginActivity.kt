package com.example.tourismhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tourismhelper.tourfragment.TouristHomeFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TouristLoginActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tourist_login)

        var loginTourist = supportActionBar
        loginTourist?.setTitle("Tourist Login")

        var registertourist = findViewById<EditText>(R.id.touristuserName)
        var password = findViewById<EditText>(R.id.touristpassWord)

        val btntouristlogin = findViewById<Button>(R.id.btntouristlogin)
        btntouristlogin.setOnClickListener {
            /*Toast.makeText(this, "Transport Selected", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, TransportMainActivity::class.java)
            startActivity(intent)*/

            val intent = Intent(this, TouristMainActivity::class.java)

            startActivity(intent)

            databaseReference = FirebaseDatabase.getInstance().getReference("tourist")
            databaseReference.child(registertourist.text.toString()).get().addOnSuccessListener {
                if(it.exists()){
                    var passwordFromDB = it.child("touristPassword").value.toString()
                    var logtourFromDB = it.child("touristUsername").value.toString()
                    if(password.text.toString() == passwordFromDB){
                        Toast.makeText(this, "Logged In Successfully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, TouristMainActivity::class.java)
                        intent.putExtra("logtour", logtourFromDB)
                        startActivity(intent)
                        registertourist.text.clear()
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