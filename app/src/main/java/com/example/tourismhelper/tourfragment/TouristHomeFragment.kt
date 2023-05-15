package com.example.tourismhelper.tourfragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tourismhelper.R
import com.example.tourismhelper.ResHotelDisplayListActivity
import com.example.tourismhelper.ResHotelMainActivity
import com.example.tourismhelper.TransportProviderActivity
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

        val logtour = arguments?.getString("logtour")

        val btnReview = view.findViewById<Button>(R.id.touristhomebutton1)
        btnReview.setOnClickListener {
            //parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerTourist,TouristReviewFragment()).commit()
        }

        val btnHotels = view.findViewById<Button>(R.id.touristhomebutton2)
        btnHotels.setOnClickListener {
            val intent = Intent(context, ResHotelDisplayListActivity::class.java)
            intent.putExtra("logtour", logtour)
            startActivity(intent)
        }

        val btnTransport = view.findViewById<Button>(R.id.touristhomebutton5)
        btnTransport.setOnClickListener {
            val intent = Intent(context, TransportProviderActivity::class.java)

            startActivity(intent)
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