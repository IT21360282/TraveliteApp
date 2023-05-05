package com.example.tourismhelper

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tourismhelper.R
import com.example.tourismhelper.TouristMainActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TouristUserProfileActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_tourist_user_profile, container, false)

        val tourusername = arguments?.getString("tourusername")

        var touristFirstname = view.findViewById<TextView>(R.id.tourprof1)
        var touristLastname = view.findViewById<TextView>(R.id.tourprof2)
        var touristEmail = view.findViewById<TextView>(R.id.tourprof3)
        var touristContactNumber = view.findViewById<TextView>(R.id.tourprof4)
        var touristBirthCountry = view.findViewById<TextView>(R.id.tourprof5)


        databaseReference = FirebaseDatabase.getInstance().getReference("tourist")
        databaseReference.child(tourusername.toString()).get().addOnSuccessListener {
            if(it.exists()){
                var touristNameFromDB = it.child("touristFirstName").value.toString()
                var tourusernameFromDB = it.child("touristUsername").value.toString()

                touristFirstname.setText(touristNameFromDB)

            }
            else{
                Toast.makeText(context, "User Does Not Exist", Toast.LENGTH_SHORT).show()
            }
        }

        return  view
    }
}