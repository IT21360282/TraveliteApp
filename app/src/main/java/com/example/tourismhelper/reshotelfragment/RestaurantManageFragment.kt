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
        val btnAddMeal = view?.findViewById<Button>(R.id.addResMeal)
        btnAddMeal?.setOnClickListener {
            val addMealFragment = HotelAddMealFragment()
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel, addMealFragment).commit()
        }
        val btnAddCombo = view?.findViewById<Button>(R.id.addResCombo)
        btnAddCombo?.setOnClickListener {
            val addComboPackFragmentFragment = AddComboPackFragment()
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel, addComboPackFragmentFragment).commit()
        }
        val btnAddDiscount = view?.findViewById<Button>(R.id.addHotelPackage)
        btnAddDiscount?.setOnClickListener {
            val addPackageFragment = HotelAddPackageFragment()
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel, addPackageFragment).commit()
        }
        return view
    }
}