package com.example.tourismhelper.tourfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tourismhelper.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TouristHomeFragment : Fragment() {

    private lateinit var databaseReference: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tourist_home, container, false)

        val btnReview = view.findViewById<Button>(R.id.touristhomebutton1)
        btnReview.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerTourist,TouristReviewFragment()).commit()
        }

        val btnLoc = view.findViewById<Button>(R.id.touridthomebutton3)
        btnLoc.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerTourist,TouristLocationFragment()).commit()
        }

        val btnFav = view.findViewById<Button>(R.id.touristhomebutton6)
        btnFav.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerTourist,TouristFavlistFragment()).commit()
        }

        val btnNotify = view.findViewById<Button>(R.id.touristhomebutton4)
        btnNotify.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerTourist,TouristNotificationFragment()).commit()
        }

        return  view
    }
}