package com.example.tourismhelper.reshotelfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.tourismhelper.R
import com.example.tourismhelper.database.HotelMealData
import com.example.tourismhelper.database.RestaurantComboData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddComboPackFragment : Fragment() {

    private lateinit var databaseReferenceCombo: DatabaseReference
    private lateinit var comboServedForTxt: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_combo_pack, container, false)

        var username = arguments?.getString("userName")

        var comboName = view.findViewById<EditText>(R.id.edtComboName)
        var comboDescription = view.findViewById<EditText>(R.id.edtComboDescription)

        var comboServedFor = view.findViewById<Spinner>(R.id.selectComboServedFor)
        comboServedFor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                comboServedForTxt = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        var comboIncludingItems = view.findViewById<EditText>(R.id.edtIncloodingComboItems)
        var comboIncludingFreeItems = view.findViewById<EditText>(R.id.edtComboFreeItem)
        var comboPrice = view.findViewById<EditText>(R.id.edtComboPrice)

        var btnSubmitCombo = view.findViewById<Button>(R.id.btnSubmitCombo)
        btnSubmitCombo.setOnClickListener {

            var comboID = comboName.text.toString().replace(" ","")

            if(comboID.isNotEmpty()) {
                databaseReferenceCombo = FirebaseDatabase.getInstance().getReference("restaurant/$username/restaurantCombo")
                val combo = RestaurantComboData(
                    comboName.text.toString(),
                    comboDescription.text.toString(),
                    comboServedForTxt,
                    comboIncludingItems.text.toString(),
                    comboIncludingFreeItems.text.toString(),
                    comboPrice.text.toString(),
                )
                databaseReferenceCombo.child(comboID).setValue(combo).addOnSuccessListener {
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