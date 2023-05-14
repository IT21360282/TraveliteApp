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

        val regNum = intent.getStringExtra("regNum").toString()
        val tregNum = intent.getStringExtra("tregNum").toString()
        val sName = intent.getStringExtra("sName").toString()


        val regNumBundle = Bundle()
        regNumBundle.putString("regNum",regNum)

        val tregNumBundle = Bundle()
        tregNumBundle.putString("tregNum",tregNum)

        val sNameBundle = Bundle()
        sNameBundle.putString("sName",sName)

        loadFragment(TransportHomeFragment(),regNumBundle)
        supportActionBar?.setTitle("Transport Home")

        loadAUpdateFragment(TransportUpdateFragment(),tregNumBundle)
        supportActionBar?.setTitle("Transport Provider Profile Update")

        /*loadActivityBook(TransportProviderBookActivity(),tregNumBundle)
        supportActionBar?.setTitle("Transport Provider Book")*/



        var transportBottomNav = findViewById<BottomNavigationView>(R.id.transportBottomNav)
        transportBottomNav.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.transportNavHome ->{
                    loadFragment(TransportHomeFragment(), regNumBundle)
                    supportActionBar?.setTitle("Transport Home")
                    true
                }R.id.transportNavHistory ->{
                    loadFragment(TransportHistoryFragment(), regNumBundle)
                    supportActionBar?.setTitle("Transport Booking History")
                    true
                }R.id.transportNavNotification ->{
                loadFragment(TransportNotificationFragment(), regNumBundle)
                supportActionBar?.setTitle("Transport Booking Notifications")
                true
                }R.id.transportNavProfile ->{
                    loadFragment(TransportProfileFragment(), regNumBundle)
                    supportActionBar?.setTitle("Transport Provider Owner Profile")
                    true
                }
                else->false
            }
        }

    }
    private fun loadFragment(fragment: Fragment, bundle: Bundle){
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerTransport,fragment).commit()
}

    private fun loadAUpdateFragment(fragment: Fragment, bundle: Bundle){
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerTransport,fragment).commit()
    }

    /*private fun loadActivityBook(fragment: Fragment, bundle: Bundle){
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerTransport,fragment).commit()
    }*/
}