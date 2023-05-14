package com.example.tourismhelper

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.example.tourismhelper.database.HotelData
import com.example.tourismhelper.database.HotelMealData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ResHotelDisplayListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_res_hotel_display_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Hotels in Sri Lanka")

        var usernameTourist = intent.getStringExtra("tourist")

        val linearLayout = findViewById<LinearLayout>(R.id.hotelList)

        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("hotel")

        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (userSnapshot in dataSnapshot.children) {

                    val linearLayoutInner = LinearLayout(this@ResHotelDisplayListActivity)
                    linearLayoutInner.orientation = LinearLayout.VERTICAL
                    linearLayoutInner.setBackgroundResource(R.drawable.styles_linear_layout)

                    val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    layoutParams.setMargins(50, 50, 50, 0)
                    linearLayoutInner.layoutParams = layoutParams
                    linearLayoutInner.gravity = Gravity.CENTER

                    linearLayoutInner.setPadding(40,30,40,30)

                    val hotels = userSnapshot.getValue(HotelData::class.java)

                    val imageView = ImageView(this@ResHotelDisplayListActivity)
                    imageView.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.baseline_image_24, null))
                    imageView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 250)

                    val hotelName =TextView(this@ResHotelDisplayListActivity)
                    hotelName.setText(hotels?.hotelName)
                    hotelName.gravity = Gravity.CENTER
                    hotelName.textSize = 30f
                    hotelName.setTypeface(null,Typeface.BOLD)

                    val ratings = hotels?.starRate

                    var ratingText = ""

                    if(ratings == "★"){
                        ratingText = "★✩✩✩✩ | 1.0"
                    }
                    else if(ratings == "★★"){
                        ratingText = "★★✩✩✩ | 2.0"
                    }
                    else if(ratings == "★★★"){
                        ratingText = "★★★✩✩ | 3.0"
                    }
                    else if(ratings == "★★★★"){
                        ratingText = "★★★★✩ | 4.0"
                    }
                    else if(ratings == "★★★★★"){
                        ratingText = "★★★★★ | 5.0"
                    }

                    val startRate =TextView(this@ResHotelDisplayListActivity)
                    startRate.setText(ratingText)
                    startRate.gravity = Gravity.CENTER
                    startRate.textSize = 20f

                    val btnLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    btnLayoutParams.setMargins(0,10,0,15)

                    val btnViewDetails = Button(this@ResHotelDisplayListActivity)
                    btnViewDetails.text = "View Details"
                    btnViewDetails.setBackgroundResource(R.drawable.btn_background)
                    btnViewDetails.setTextColor(resources.getColor(R.color.white,null))
                    btnViewDetails.layoutParams = btnLayoutParams

                    btnViewDetails.setOnClickListener {
                        var intent = Intent(this@ResHotelDisplayListActivity,HotelProfileViewActivity::class.java)
                        intent.putExtra("userName",userSnapshot.key.toString() )
                        startActivity(intent)
                    }

                    linearLayoutInner.addView(imageView)
                    linearLayoutInner.addView(hotelName)
                    linearLayoutInner.addView(startRate)
                    linearLayoutInner.addView(btnViewDetails)


                    linearLayout.addView(linearLayoutInner)

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        /*var btnView = findViewById<Button>(R.id.viewDetails)
        btnView.setOnClickListener {
            var intent = Intent(this, HotelProfileViewActivity::class.java)
            startActivity(intent)
        }*/
    }
}