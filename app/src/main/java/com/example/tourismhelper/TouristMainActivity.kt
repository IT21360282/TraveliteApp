package com.example.tourismhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tourismhelper.tourfragment.TouristHistoryFragment
import com.example.tourismhelper.tourfragment.TouristHomeFragment
import com.example.tourismhelper.tourfragment.TouristProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class TouristMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tourist_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)


        val logtour = intent.getStringExtra("logtour").toString()

        val logtourBundle = Bundle()
        logtourBundle.putString("logtour",logtour)

        loadFragment(TouristHomeFragment(), logtourBundle)
        supportActionBar?.setTitle("Transport Home")

        var TouristBtnNav = findViewById<BottomNavigationView>(R.id.TouristBtnNav)
        TouristBtnNav.setOnItemSelectedListener { item->

            when(item.itemId){
                R.id.tournavHome ->{
                    loadFragment(TouristHomeFragment(), logtourBundle)
                     true
                }R.id.tournavHistory->{
                loadFragment(TouristHistoryFragment(), logtourBundle)
                 true
            }R.id.tournavProfile ->{
                loadFragment(TouristProfileFragment(), logtourBundle)
                 true
            }
                else->false
            }
        }

    }
    private fun loadFragment(fragment: Fragment, bandle: Bundle){
        fragment.arguments = bandle
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerTourist,fragment).commit()
    }
}