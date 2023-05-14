package com.example.tourismhelper.reshotelfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.tourismhelper.R
import com.example.tourismhelper.database.HotelMealData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RestaurantAddMealFragment : Fragment() {

    private lateinit var databaseReferenceMeal: DatabaseReference
    private lateinit var mealServedForTxt: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_restaurant_add_meal, container, false)

        var username = arguments?.getString("userName")

        var mealName = view.findViewById<EditText>(R.id.edtMealName)
        var mealDescription = view.findViewById<EditText>(R.id.edtMealDescription)

        var mealServedFor = view.findViewById<Spinner>(R.id.selectServedFor)
        mealServedFor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mealServedForTxt = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        var mealIncludingItems = view.findViewById<EditText>(R.id.edtIncloodingMealItem)
        var mealPrice = view.findViewById<EditText>(R.id.edtMealPrice)

        //Served For Time
        var breakfirst = view.findViewById<CheckBox>(R.id.cbBreakfirst)
        var lunch = view.findViewById<CheckBox>(R.id.cbLunch)
        var teaTime = view.findViewById<CheckBox>(R.id.cbTeaTime)
        var dinner = view.findViewById<CheckBox>(R.id.cbDinner)

        var btnSubmitMeal = view.findViewById<Button>(R.id.btnSubmitMeal)
        btnSubmitMeal.setOnClickListener {

            var mealID = mealName.text.toString().replace(" ","")

            if(mealID.isNotEmpty()) {
                databaseReferenceMeal =
                    FirebaseDatabase.getInstance().getReference("restaurant/$username/restaurantMeals")
                val user = HotelMealData(
                    mealName.text.toString(),
                    mealDescription.text.toString(),
                    mealServedForTxt,
                    mealIncludingItems.text.toString(),
                    mealPrice.text.toString(),
                    breakfirst.isChecked,
                    lunch.isChecked,
                    teaTime.isChecked,
                    dinner.isChecked
                )
                databaseReferenceMeal.child(mealID).setValue(user).addOnSuccessListener {
                    Toast.makeText(context, "Meal is Added Successfully", Toast.LENGTH_SHORT).show()

                    val restaurantManageFragment = RestaurantManageFragment()
                    parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel, restaurantManageFragment).commit()

                }.addOnFailureListener {
                    Toast.makeText(context, "Failed to Add, Try Again", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(context, "Enter a Valid Meal Name", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}