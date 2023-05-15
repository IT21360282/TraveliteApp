package com.example.tourismhelper.reshotelfragment

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.tourismhelper.R
import com.example.tourismhelper.database.HotelBooking
import com.example.tourismhelper.database.HotelMealData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ResHotelHistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_res_hotel_history, container, false)

        //get userName from parent activity (Hotel Main Activity)
        var username = arguments?.getString("userName")

        val linearLayout = view.findViewById<LinearLayout>(R.id.displayHistoryContainer)

        //popup overlay
        val overlayView = View(requireContext())
        overlayView.setBackgroundResource(R.drawable.popup_overlay)
        overlayView.alpha = 1f
        overlayView.isClickable = false
        val container = view.findViewById<FrameLayout>(R.id.popupContainer)

        //get all meal's data from database
        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("hotel/$username/hotelBooking")

        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (userSnapshot in dataSnapshot.children) {

                    val linearLayoutInner = LinearLayout(context)
                    linearLayoutInner.orientation = LinearLayout.VERTICAL
                    linearLayoutInner.setBackgroundResource(R.drawable.styles_linear_layout)

                    val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    layoutParams.setMargins(50, 10, 50, 30)
                    linearLayoutInner.layoutParams = layoutParams
                    linearLayoutInner.gravity = Gravity.CENTER

                    linearLayoutInner.setPadding(40,30,40,30)

                    val booking = userSnapshot.getValue(HotelBooking::class.java)


                    val tvRoomName = TextView(context)
                    tvRoomName.text = booking!!.roomName
                    tvRoomName.setTextColor(resources.getColor(R.color.main_color,null))
                    tvRoomName.textSize = 22f
                    tvRoomName.gravity = Gravity.CENTER
                    tvRoomName.setTypeface(null, Typeface.BOLD)
                    tvRoomName.setPadding(0,0,0,10)

                    val btnLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    btnLayoutParams.setMargins(0,0,0,15)

                    val roomName = TextView(context)
                    roomName.text = "Served For:"
                    roomName.textSize = 20f
                    roomName.setTypeface(null, Typeface.BOLD)
                    val tvRoomNameBody = TextView(context)
                    tvRoomNameBody.text = booking!!.roomName
                    tvRoomNameBody.textSize = 18f
                    tvRoomNameBody.setPadding(10,0,0,15)

                    val bookingID = TextView(context)
                    bookingID.text = "BookingID:"
                    bookingID.textSize = 20f
                    bookingID.setTypeface(null, Typeface.BOLD)
                    val tvBookingID = TextView(context)
                    tvBookingID.text = booking!!.bookingID
                    tvBookingID.textSize = 18f
                    tvBookingID.setPadding(10,0,0,15)

                    val customerName = TextView(context)
                    customerName.text = "Customer Name:"
                    customerName.textSize = 20f
                    customerName.setTypeface(null, Typeface.BOLD)
                    val tvCustomerName = TextView(context)
                    tvCustomerName.text = "${booking!!.touristName}"
                    tvCustomerName.textSize = 18f
                    tvCustomerName.setPadding(10,0,0,25)

                    val btnMore = Button(context)
                    btnMore.text = "More"
                    btnMore.setBackgroundResource(R.drawable.btn_background)
                    btnMore.setTextColor(resources.getColor(R.color.white,null))
                    btnMore.layoutParams = btnLayoutParams

                    btnMore.setOnClickListener{


                    }

                    linearLayoutInner.addView(tvRoomName)
                    linearLayoutInner.addView(roomName)
                    linearLayoutInner.addView(tvRoomNameBody)
                    linearLayoutInner.addView(bookingID)
                    linearLayoutInner.addView(tvBookingID)
                    linearLayoutInner.addView(customerName)
                    linearLayoutInner.addView(tvCustomerName)
                    linearLayoutInner.addView(btnMore)

                    linearLayout.addView(linearLayoutInner)
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {

            }
        })



        return view
    }
}