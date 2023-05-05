package com.example.tourismhelper.tourfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tourismhelper.R
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

        val tourusername = arguments?.getString("tourusername")

        var touristFirstname = view.findViewById<TextView>(R.id.userFirstName)
        var touristLastname = view.findViewById<TextView>(R.id.userLastName)
        var touristEmail = view.findViewById<TextView>(R.id.userEmail)
        var touristContactNumber = view.findViewById<TextView>(R.id.userPhone)
        var touristBirthCountry = view.findViewById<TextView>(R.id.userCountry)


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