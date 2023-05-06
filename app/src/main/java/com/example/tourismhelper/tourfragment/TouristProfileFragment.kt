package com.example.tourismhelper.tourfragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tourismhelper.R
import com.example.tourismhelper.TouristMainActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TouristProfileFragment : Fragment() {


    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tourist_profile, container, false)

        val logtour = arguments?.getString("logtour")

        var tourfirstname = view.findViewById<TextView>(R.id.tourname11)
        var tourlastname = view.findViewById<TextView>(R.id.tourlast12)
        var touremail = view.findViewById<TextView>(R.id.tourmail13)
        var tourcontact = view.findViewById<TextView>(R.id.tournum14)
        var tourcountry = view.findViewById<TextView>(R.id.tourcountry15)

        databaseReference = FirebaseDatabase.getInstance().getReference("tourist")
        databaseReference.child(logtour.toString()).get().addOnSuccessListener {
            if(it.exists()){
                var touristfirstFromDB = it.child("touristFirstname").value.toString()
                var touristlastFromDB = it.child("touristLastname").value.toString()
                var touristmailFromDB = it.child("touristEmail").value.toString()
                var touristcontactFromDB = it.child("touristContactNumber").value.toString()
                var touristcountryFromDB = it.child("touristBirthCountry").value.toString()

                tourfirstname.setText(touristfirstFromDB)
                tourlastname.setText(touristlastFromDB)
                touremail.setText(touristmailFromDB)
                tourcontact.setText(touristcontactFromDB)
                tourcountry.setText(touristcountryFromDB)

            }

            else{
                Toast.makeText(context, "User Does Not Exist", Toast.LENGTH_SHORT).show()
            }
        }

        return  view

    }
}