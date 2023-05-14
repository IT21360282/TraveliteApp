package com.example.tourismhelper


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tourismhelper.reshotelfragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ResHotelMainActivity : AppCompatActivity() {

    private lateinit var databaseReferenceOwner: DatabaseReference
    private lateinit var databaseReferenceHotel: DatabaseReference

    private var fullNameTxt: String = ""
    private var userNameTxt: String = ""
    private var passwordTxt: String = ""
    private var bTypeTxt: String = ""
    private var emailTxt: String = ""
    private var phoneTxt: String = ""

    private var hotelNameTxt: String = ""
    private var hotelRegNumTxt: String = ""
    private var businessEmail: String = ""
    private var businessPhone: String = ""
    private var starRate: String = ""
    private var businessLocation: String = ""
    private var countRooms: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_res_hotel_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        var userName = intent.getStringExtra("userName").toString()

        databaseReferenceOwner = FirebaseDatabase.getInstance().getReference("hotel_restaurant_Owner")
        databaseReferenceOwner.child(userName).get().addOnSuccessListener {
            if(it.exists()){
                fullNameTxt = it.child("fullName").value.toString()
                userNameTxt = it.child("userName").value.toString()
                passwordTxt = it.child("password").value.toString()
                bTypeTxt = it.child("btype").value.toString()
                emailTxt = it.child("email").value.toString()
                phoneTxt = it.child("phone").value.toString()
            }
            else{
                Toast.makeText(this, "User Does not Exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this, "Failed to Get Data, Try Again", Toast.LENGTH_SHORT).show()
        }

        databaseReferenceHotel = FirebaseDatabase.getInstance().getReference("hotel")
        databaseReferenceHotel.child(userName).get().addOnSuccessListener {
            if(it.exists()){
                hotelNameTxt = it.child("hotelName").value.toString()
                hotelRegNumTxt = it.child("hotelRegNum").value.toString()
                businessEmail = it.child("businessEmail").value.toString()
                businessPhone = it.child("businessPhone").value.toString()
                starRate = it.child("starRate").value.toString()
                businessLocation = it.child("businessLocation").value.toString()
                countRooms = it.child("countRooms").value.toString()
            }
            else{
                //Toast.makeText(this, "User Does not Exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            //Toast.makeText(this, "Failed to Get Data, Try Again", Toast.LENGTH_SHORT).show()
        }

        var userNameBundle = Bundle()
        userNameBundle.putString("userName", userName)

        loadFragment(ResHotelHomeFragment(),userNameBundle)
        supportActionBar?.setTitle("Hotel Home")


        var resHotelBottomNav = findViewById<BottomNavigationView>(R.id.resHotelBottomNav)
        resHotelBottomNav.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.resHotelNavHome ->{
                    loadFragment(ResHotelHomeFragment(),userNameBundle)
                    supportActionBar?.setTitle("Hotel Home")
                    true
                }R.id.resHotelNavIncome ->{
                    loadFragment(ResHotelIncomeFragment(),userNameBundle)
                    supportActionBar?.setTitle("Hotel Income and Maintain")
                    true
                }R.id.resHotelNavHistory ->{
                    loadFragment(ResHotelHistoryFragment(),userNameBundle)
                    supportActionBar?.setTitle("Hotel Booking History")
                    true
                }R.id.resHotelNavUser ->{
                    loadFragment(ResHotelProfileFragment(),userNameBundle)
                    supportActionBar?.setTitle("Hotel Owner Profile")
                    true
                }
                else->false
            }
        }

    }
    private fun loadFragment(fragment:Fragment, bundle:Bundle){
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel,fragment).commit()
    }
}