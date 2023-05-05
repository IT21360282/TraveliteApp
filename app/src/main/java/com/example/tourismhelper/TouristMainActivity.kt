package com.example.tourismhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tourismhelper.tourfragment.TouristHistoryFragment
import com.example.tourismhelper.tourfragment.TouristProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class TouristMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tourist_main)

        var TouristBtnNav = findViewById<BottomNavigationView>(R.id.TouristBtnNav)
        TouristBtnNav.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.tournavHome ->{
                    loadFragment(TouristHomeFragment())
                     true
                }R.id.tournavHistory->{
                loadFragment(TouristHistoryFragment())
                 true
            }R.id.tournavProfile ->{
                loadFragment(TouristProfileFragment())
                 true
            }
                else->false
            }
        }

    }
    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerTourist,fragment).commit()
    }
}