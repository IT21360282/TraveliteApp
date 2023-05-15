package com.example.tourismhelper.tourfragment

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
import com.example.tourismhelper.R
import com.example.tourismhelper.touristDatabase.Locations
import com.example.tourismhelper.touristDatabase.reviews
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TouristNotificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tourist_notification, container, false)

        val linearLayout = view.findViewById<LinearLayout>(R.id.locationsContainer)

        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("reviews")

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

                    val reviews = userSnapshot.getValue(reviews::class.java)



                    val review = TextView(context)
                    review.setText(reviews?.review)
                    review.gravity = Gravity.CENTER
                    review.textSize = 20f

                    val date = TextView(context)
                    date.setText(reviews?.date)
                    date.gravity = Gravity.CENTER
                    date.textSize = 20f




                    linearLayoutInner.addView(review)
                    linearLayoutInner.addView(date)



                    linearLayout.addView(linearLayoutInner)

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })


        return  view
    }
}