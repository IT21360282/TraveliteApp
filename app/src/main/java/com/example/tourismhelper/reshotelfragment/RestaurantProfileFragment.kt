package com.example.tourismhelper.reshotelfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.tourismhelper.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RestaurantProfileFragment : Fragment() {

    private lateinit var databaseReferenceOwner: DatabaseReference
    private lateinit var databaseReferenceRestaurant: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_restaurant_profile, container, false)

        var username = arguments?.getString("userName")

        var profRestaurantName = view.findViewById<TextView>(R.id.tvBusinessNameValue)
        var profOwnerName = view.findViewById<TextView>(R.id.tvNameValue)

        //owner details
        var profOwnerNameBody = view.findViewById<TextView>(R.id.tvOwnerName)
        var profOwnerEmail = view.findViewById<TextView>(R.id.tvOwnerEmail)
        var profOwnerPhone = view.findViewById<TextView>(R.id.tvOwnerPhone)

        //business details
        var profRestaurantNameBody = view.findViewById<TextView>(R.id.tvRestaurantName)
        var profBusinessType = view.findViewById<TextView>(R.id.tvBusinessType)
        var profBusinessEmail = view.findViewById<TextView>(R.id.tvBusinessEmailVal)
        var profBusinessPhone = view.findViewById<TextView>(R.id.tvBusinessPhoneValue)
        var profBusinessLocation = view.findViewById<TextView>(R.id.tvBusinessLocation)

        databaseReferenceOwner = FirebaseDatabase.getInstance().getReference("hotel_restaurant_Owner")
        databaseReferenceOwner.child(username.toString()).get().addOnSuccessListener {
            if(it.exists()){
                var fullNameFromDB = it.child("fullName").value.toString()
                var userNameFromDB = it.child("userName").value.toString()
                var emailFromDB = it.child("email").value.toString()
                var phoneFromDB = it.child("phone").value.toString()
                var bTypeFromDB = it.child("btype").value.toString()

                profOwnerName.text = "$fullNameFromDB"
                profOwnerNameBody.text = "$fullNameFromDB"
                profOwnerEmail.text = "$emailFromDB"
                profOwnerPhone.text = "$phoneFromDB"
                profBusinessType.text = "$bTypeFromDB"
            }
            else{
                //Toast.makeText(this, "User Does not Exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{

        }

        databaseReferenceRestaurant = FirebaseDatabase.getInstance().getReference("restaurant")
        databaseReferenceRestaurant.child(username.toString()).get().addOnSuccessListener {
            if(it.exists()){
                var restaurantNameFromDB = it.child("restaurantName").value.toString()
                var restaurantRegNumFromDB = it.child("restaurantRegNum").value.toString()
                var bEmailFromDB = it.child("businessEmail").value.toString()
                var bPhoneFromDB = it.child("businessPhone").value.toString()
                var bLocationFromDB = it.child("businessLocation").value.toString()

                profRestaurantName.setText("$restaurantNameFromDB")
                profRestaurantNameBody.setText("$restaurantNameFromDB")
                profBusinessEmail.setText("$bEmailFromDB")
                profBusinessPhone.setText("$bPhoneFromDB")
                profBusinessLocation.setText("$bLocationFromDB")
            }
            else{
                //Toast.makeText(this, "User Does not Exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{

        }

        return view
    }
}