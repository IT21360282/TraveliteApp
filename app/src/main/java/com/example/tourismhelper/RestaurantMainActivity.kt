package com.example.tourismhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tourismhelper.reshotelfragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DatabaseReference

class RestaurantMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        var userName = intent.getStringExtra("userName").toString()

        var userNameBundle = Bundle()
        userNameBundle.putString("userName", userName)

        loadFragment(RestaurantHomeFragment(), userNameBundle)
        supportActionBar?.setTitle("Restaurant Home")

        var restaurantBottomNav = findViewById<BottomNavigationView>(R.id.restaurantBottomNav)
        restaurantBottomNav.setOnItemSelectedListener { NavBarItems->
            when(NavBarItems.itemId){
                R.id.restaurantNavHome ->{
                    loadFragment(RestaurantHomeFragment(), userNameBundle)
                    supportActionBar?.setTitle("Restaurant Home")
                    true
                }R.id.restaurantNavManage ->{
                    loadFragment(RestaurantManageFragment(), userNameBundle)
                    supportActionBar?.setTitle("Restaurant Profile Manage")
                    true
                }R.id.restaurantNavUser ->{
                    loadFragment(RestaurantProfileFragment(), userNameBundle)
                    supportActionBar?.setTitle("Restaurant Owner Profile")
                    true
                }
                else->false
            }
        }

    }
    private fun loadFragment(fragment: Fragment, bundle:Bundle){
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel,fragment).commit()
    }

}