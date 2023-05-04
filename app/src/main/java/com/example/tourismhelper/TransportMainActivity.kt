package com.example.tourismhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.tourismhelper.transportfragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class TransportMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transport_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        loadFragment(TransportHomeFragment())
        supportActionBar?.setTitle("Transport Home")

        var transportBottomNav = findViewById<BottomNavigationView>(R.id.transportBottomNav)
        transportBottomNav.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.transportNavHome ->{
                    loadFragment(TransportHomeFragment())
                    supportActionBar?.setTitle("Transport Home")
                    true
                }R.id.transportNavHistory ->{
                    loadFragment(TransportHistoryFragment())
                    supportActionBar?.setTitle("Transport Booking History")
                    true
                }R.id.transportNavProfile ->{
                    loadFragment(TransportProfileFragment())
                    supportActionBar?.setTitle("Transport Provider Owner Profile")
                    true
                }
                else->false
            }
        }

    }
    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerTransport,fragment).commit()
}
}