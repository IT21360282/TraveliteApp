package com.example.tourismhelper.tourfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.tourismhelper.R
import com.google.firebase.database.FirebaseDatabase

class TouristLocationProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tourist_location_profile, container, false)

        val locID = arguments?.getString("locID")

        var nameLoc = view.findViewById<TextView>(R.id.nameLoc)
        var descriptionLoc = view.findViewById<TextView>(R.id.descriptionLoc)
        var cityLoc = view.findViewById<TextView>(R.id.CityLoc)
        var provinceLoc = view.findViewById<TextView>(R.id.provinceLoc)
        var distance = view.findViewById<TextView>(R.id.distanceLoc)
        var address = view.findViewById<TextView>(R.id.addressLoc)

        var database = FirebaseDatabase.getInstance().getReference("locations")
        database.child(locID.toString()).get().addOnSuccessListener {
            if(it.exists()){
                var locNameFromDB = it.child("locName").value.toString()
                var desFromDB = it.child("description").value.toString()
                var cityFromDB = it.child("city").value.toString()
                var ProvinceFromDB = it.child("province").value.toString()
                var distanceFromDB = it.child("distance").value.toString()
                var addressFromDB = it.child("address").value.toString()

                nameLoc.setText("$locNameFromDB")
                descriptionLoc.setText("$desFromDB")
                cityLoc.setText("$cityFromDB")
                provinceLoc.setText("$ProvinceFromDB")
                distance.setText("$distanceFromDB")
                address.setText("$addressFromDB")
            }
            else{
                //Toast.makeText(this, "User Does not Exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{

        }

        return view
    }
}