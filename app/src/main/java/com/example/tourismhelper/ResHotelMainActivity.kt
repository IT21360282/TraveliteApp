package com.example.tourismhelper


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.tourismhelper.reshotelfragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class ResHotelMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_res_hotel_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        loadFragment(ResHotelHomeFragment())
        supportActionBar?.setTitle("Hotel Home")

        var resHotelBottomNav = findViewById<BottomNavigationView>(R.id.resHotelBottomNav)
        resHotelBottomNav.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.resHotelNavHome ->{
                    loadFragment(ResHotelHomeFragment())
                    supportActionBar?.setTitle("Hotel Home")
                    true
                }R.id.resHotelNavIncome ->{
                    loadFragment(ResHotelIncomeFragment())
                    supportActionBar?.setTitle("Hotel Income and Maintain")
                    true
                }R.id.resHotelNavHistory ->{
                    loadFragment(ResHotelHistoryFragment())
                    supportActionBar?.setTitle("Hotel Booking History")
                    true
                }R.id.resHotelNavUser ->{
                    loadFragment(ResHotelProfileFragment())
                    supportActionBar?.setTitle("Hotel Owner Profile")
                    true
                }
                else->false
            }
        }

    }
    private fun loadFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel,fragment).commit()
    }
}