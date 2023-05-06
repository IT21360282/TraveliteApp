package com.example.tourismhelper.tourfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.tourismhelper.R

class TouristLocationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tourist_location, container, false)
        val btnView = view.findViewById<Button>(R.id.view1)
        btnView.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerTourist,TouristLocationProfileFragment()).commit()
        }
        return view
    }
}