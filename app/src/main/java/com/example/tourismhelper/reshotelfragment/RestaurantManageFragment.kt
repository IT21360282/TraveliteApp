package com.example.tourismhelper.reshotelfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.tourismhelper.R

class RestaurantManageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_restaurant_manage, container, false)

        var username = arguments?.getString("userName")

        var userNameBundle = Bundle()
        userNameBundle.putString("userName", username)

        val btnAddMeal = view?.findViewById<Button>(R.id.addResMeal)
        btnAddMeal?.setOnClickListener {
            val addMealFragment = RestaurantAddMealFragment()
            addMealFragment.arguments = userNameBundle
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel, addMealFragment).commit()
        }
        val btnAddCombo = view?.findViewById<Button>(R.id.addResCombo)
        btnAddCombo?.setOnClickListener {
            val addComboPackFragmentFragment = AddComboPackFragment()
            addComboPackFragmentFragment.arguments = userNameBundle
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel, addComboPackFragmentFragment).commit()
        }

        return view
    }
}