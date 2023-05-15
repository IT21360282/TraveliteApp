package com.example.tourismhelper.tourfragment

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.tourismhelper.HotelProfileViewActivity
import com.example.tourismhelper.R
import com.example.tourismhelper.database.HotelData
import com.example.tourismhelper.reshotelfragment.HotelAddRoomFragment
import com.example.tourismhelper.touristDatabase.Locations
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TouristLocationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tourist_location, container, false)


        val linearLayout = view.findViewById<LinearLayout>(R.id.locationsContainer)

        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("locations")

        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (userSnapshot in dataSnapshot.children) {

                    val linearLayoutInner = LinearLayout(context)
                    linearLayoutInner.orientation = LinearLayout.VERTICAL
                    linearLayoutInner.setBackgroundResource(R.drawable.styles_linear_layout)

                    val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    layoutParams.setMargins(50, 50, 50, 0)
                    linearLayoutInner.layoutParams = layoutParams
                    linearLayoutInner.gravity = Gravity.CENTER

                    linearLayoutInner.setPadding(40,30,40,30)

                    val locations = userSnapshot.getValue(Locations::class.java)

                    val imageView = ImageView(context)
                    imageView.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.baseline_image_24, null))
                    imageView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 250)

                    val locName = TextView(context)
                    locName.setText(locations?.locName)
                    locName.gravity = Gravity.CENTER
                    locName.textSize = 30f
                    locName.setTypeface(null, Typeface.BOLD)

                    val province =TextView(context)
                    province.setText(locations?.province)
                    province.gravity = Gravity.CENTER
                    province.textSize = 20f

                    val city =TextView(context)
                    city.setText(locations?.city)
                    city.gravity = Gravity.CENTER
                    city.textSize = 20f


                    val btnLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    btnLayoutParams.setMargins(0,10,0,15)

                    val btnViewDetails = Button(context)
                    btnViewDetails.text = "View All"
                    btnViewDetails.setBackgroundResource(R.drawable.btn_background)
                    btnViewDetails.setTextColor(resources.getColor(R.color.white,null))
                    btnViewDetails.layoutParams = btnLayoutParams



                    btnViewDetails.setOnClickListener {
                        var locID = Bundle()
                        locID.putString("locID", userSnapshot.key)

                        val locationView = TouristLocationProfileFragment()
                        TouristLocationProfileFragment().arguments = locID
                        parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel, TouristLocationProfileFragment()).commit()

                    }

                    linearLayoutInner.addView(locName)
                    linearLayoutInner.addView(province)
                    linearLayoutInner.addView(city)
                    linearLayoutInner.addView(btnViewDetails)


                    linearLayout.addView(linearLayoutInner)

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })


        /*val btnView = view.findViewById<Button>(R.id.view1)
        btnView.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerTourist,TouristLocationProfileFragment()).commit()
        }*/
        return view
    }
}