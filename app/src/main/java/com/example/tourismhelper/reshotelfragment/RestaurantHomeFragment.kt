package com.example.tourismhelper.reshotelfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.tourismhelper.R

class RestaurantHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_restaurant_home, container, false)

        var username = arguments?.getString("userName")

        val btnAllMeals = view?.findViewById<Button>(R.id.btnAllMeals)
        btnAllMeals?.setOnClickListener {
            var userNameBundle = Bundle()
            userNameBundle.putString("userName", username)

            val allRestaurantMeals = RestaurantMealDisplayFragment()
            allRestaurantMeals.arguments = userNameBundle
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel, allRestaurantMeals).commit()
        }

        val btnAllCombo = view?.findViewById<Button>(R.id.btnAllCombo)
        btnAllCombo?.setOnClickListener {
            var userNameBundle = Bundle()
            userNameBundle.putString("userName", username)

            val allRestaurantCombo = RestaurantComboPackDisplayFragment()
            allRestaurantCombo.arguments = userNameBundle
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel, allRestaurantCombo).commit()
        }

        return view
    }
}