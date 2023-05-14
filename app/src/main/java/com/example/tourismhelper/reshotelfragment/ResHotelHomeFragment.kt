package com.example.tourismhelper.reshotelfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.tourismhelper.R

class ResHotelHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_res_hotel_home, container, false)

        var username = arguments?.getString("userName")

        val btnAllRooms = view?.findViewById<Button>(R.id.btnAllRooms)
        btnAllRooms?.setOnClickListener {
            var userNameBundle = Bundle()
            userNameBundle.putString("userName", username)

            val allHotelRooms = HotelRoomMealPackageDisplayFragment()
            allHotelRooms.arguments = userNameBundle
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel, allHotelRooms).commit()
        }

        val btnAllMeals = view?.findViewById<Button>(R.id.btnAllMeals)
        btnAllMeals?.setOnClickListener {
            var userNameBundle = Bundle()
            userNameBundle.putString("userName", username)

            val allHotelMeals = HotelMealDisplayFragment()
            allHotelMeals.arguments = userNameBundle
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel, allHotelMeals).commit()
        }

        val btnAllPackages = view?.findViewById<Button>(R.id.btnAllPackages)
        btnAllPackages?.setOnClickListener {
            var userNameBundle = Bundle()
            userNameBundle.putString("userName", username)

            val allHotelPackages = HotelPackageDisplayFragment()
            allHotelPackages.arguments = userNameBundle
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel, allHotelPackages).commit()
        }

        return view
    }
}