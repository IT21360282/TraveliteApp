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

class HotelMealUpdateFragment : Fragment() {

    private lateinit var databaseReferenceMeal: DatabaseReference
    private lateinit var mealServedForTxt: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hotel_meal_update, container, false)

        var username = arguments?.getString("userName")
        var mealID = arguments?.getString("mealID")

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

        databaseReferenceMeal = FirebaseDatabase.getInstance().getReference("hotel/${username.toString()}/hotelMeals")
        databaseReferenceMeal.child(mealID.toString()).get().addOnSuccessListener {
            if(it.exists()){
                var mealNameFromDB = it.child("mealName").value.toString()
                var mealDescriptionFromDB = it.child("mealDescription").value.toString()
                var mealPriceFromDB = it.child("mealPrice").value.toString()
                var mealServedForFromDB = it.child("mealServedFor").value.toString()
                var mealIncludingItemsFromDB = it.child("mealIncludingItems").value.toString()
                var breakfirstRfomDB = it.child("breakfirst").value.toString()
                var lunchFromDB = it.child("lunch").value.toString()
                var teaTimeFromDB = it.child("teaTime").value.toString()
                var dinnerFromDB = it.child("dinner").value.toString()


                if(breakfirstRfomDB=="true"){
                    breakfirst.isChecked = true
                }
                if(lunchFromDB=="true"){
                    lunch.isChecked = true
                }
                if(teaTimeFromDB=="true"){
                    teaTime.isChecked = true
                }
                if(dinnerFromDB=="true"){
                    dinner.isChecked = true
                }


                mealName.setText("$mealNameFromDB")
                mealDescription.setText("$mealDescriptionFromDB")
                mealPrice.setText("$mealPriceFromDB")

                val adapterPayFor = ArrayAdapter.createFromResource(
                    requireContext(),
                    R.array.served_person,
                    android.R.layout.simple_spinner_item
                )
                mealServedFor.adapter = adapterPayFor

                if(mealServedForFromDB=="1 Person"){
                    mealServedFor.setSelection(0)
                }
                else if(mealServedForFromDB=="2 Persons"){
                    mealServedFor.setSelection(1)
                }
                else if(mealServedForFromDB=="3 Persons"){
                    mealServedFor.setSelection(2)
                }
                else if(mealServedForFromDB=="4 Persons"){
                    mealServedFor.setSelection(3)
                }
                else if(mealServedForFromDB=="6 Persons"){
                    mealServedFor.setSelection(4)
                }
                else if(mealServedForFromDB=="10 Persons"){
                    mealServedFor.setSelection(5)
                }
                else if(mealServedForFromDB=="Other"){
                    mealServedFor.setSelection(6)
                }



            }
            else{
                //Toast.makeText(this, "User Does not Exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{

        }

        var btnSubmitMeal = view.findViewById<Button>(R.id.btnSubmitMeal)
        btnSubmitMeal.setOnClickListener {

            var mealID = mealName.text.toString().replace(" ","")

            val dataSrt = mapOf<String,String>(
                "mealName" to mealName.text.toString(),
                "mealDescription" to mealDescription.text.toString(),
                "mealPrice" to mealPrice.text.toString(),
                "mealServedFor" to mealServedForTxt,
                "mealIncludingItems" to mealIncludingItems.text.toString(),
            )
            val dataBoolean = mapOf<String,Boolean>(
                "breakfirst" to breakfirst.isChecked,
                "lunch" to lunch.isChecked,
                "teaTime" to teaTime.isChecked,
                "dinner" to dinner.isChecked,
            )

            if(mealDescription.text.isNotEmpty() && mealPrice.text.isNotEmpty()){
                updateMeal(dataSrt,dataBoolean,mealID,username)

            }

            else{
                Toast.makeText(context, "Please Fill All Fields", Toast.LENGTH_SHORT).show()
            }


        }
        return  view
    }

    private fun updateMeal(dataSrt: Map<String, String>, dataBoolean: Map<String, Boolean>, mealID: String, username: String?) {

        databaseReferenceMeal = FirebaseDatabase.getInstance().getReference("hotel/${username.toString()}/hotelMeals")

        databaseReferenceMeal.child(mealID.toString()).updateChildren(dataSrt).addOnSuccessListener {
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show()


        }.addOnFailureListener {

        }
        databaseReferenceMeal.child(mealID.toString()).updateChildren(dataBoolean).addOnSuccessListener {
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show()


        }.addOnFailureListener {

        }
    }
}