package com.example.tourismhelper


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tourismhelper.reshotelfragment.ResHotelHistoryFragment
import com.example.tourismhelper.reshotelfragment.ResHotelHomeFragment
import com.example.tourismhelper.reshotelfragment.ResHotelIncomeFragment
import com.example.tourismhelper.reshotelfragment.ResHotelProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class ResHotelMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_res_hotel_main)

        var resHotelBottomNav = findViewById<BottomNavigationView>(R.id.resHotelBottomNav)
        resHotelBottomNav.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.resHotelNavHome ->{
                    loadFragment(ResHotelHomeFragment())
                    return@setOnItemSelectedListener true
                }R.id.resHotelNavIncome ->{
                    loadFragment(ResHotelIncomeFragment())
                    return@setOnItemSelectedListener true
                }R.id.resHotelNavHistory ->{
                    loadFragment(ResHotelHistoryFragment())
                    return@setOnItemSelectedListener true
                }R.id.resHotelNavUser ->{
                    loadFragment(ResHotelProfileFragment())
                    return@setOnItemSelectedListener true
                }
                else->false
            }
        }
        loadFragment(ResHotelHomeFragment())
    }
    private fun loadFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel,fragment).commit()
    }
}