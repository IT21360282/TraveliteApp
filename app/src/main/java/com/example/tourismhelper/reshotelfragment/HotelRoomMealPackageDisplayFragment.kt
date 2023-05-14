package com.example.tourismhelper.reshotelfragment

import android.graphics.Typeface
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import com.example.tourismhelper.R
import com.example.tourismhelper.database.HotelRoomData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HotelRoomMealPackageDisplayFragment : Fragment() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hotel_room_meal_package_display, container, false)

        var username = arguments?.getString("userName")

        val linearLayout = view.findViewById<LinearLayout>(R.id.displayContainer)

        //popup overlay
        val overlayView = View(requireContext())
        overlayView.setBackgroundResource(R.drawable.popup_overlay)
        overlayView.alpha = 1f
        overlayView.isClickable = false
        val container = view.findViewById<FrameLayout>(R.id.popupRoomContainer)

        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("hotel/$username/hotelRooms")

        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (userSnapshot in dataSnapshot.children) {


                    val linearLayoutInner = LinearLayout(context)
                    linearLayoutInner.orientation = LinearLayout.VERTICAL
                    linearLayoutInner.setBackgroundResource(R.drawable.styles_linear_layout)

                    val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    layoutParams.setMargins(50, 0, 50, 30)
                    linearLayoutInner.layoutParams = layoutParams
                    linearLayoutInner.gravity =Gravity.CENTER

                    linearLayoutInner.setPadding(40,30,40,30)

                    val room = userSnapshot.getValue(HotelRoomData::class.java)

                    val tvRoomName = TextView(context)
                    tvRoomName.text = room!!.roomName
                    tvRoomName.setTextColor(resources.getColor(R.color.main_color,null))
                    tvRoomName.textSize = 22f
                    tvRoomName.setTypeface(null,Typeface.BOLD)
                    tvRoomName.setPadding(0,0,0,10)

                    val btnLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    btnLayoutParams.setMargins(0,0,0,15)

                    val tvRoomDes = TextView(context)
                    tvRoomDes.text = room!!.roomDescription
                    tvRoomDes.textSize = 15f
                    tvRoomDes.setPadding(0,0,0,15)

                    val tvRoomPrice = TextView(context)
                    tvRoomPrice.text = "Price: ${room!!.roomPrice}"
                    tvRoomPrice.textSize = 18f
                    tvRoomPrice.setPadding(0,0,0,25)

                    val btnUpdate = Button(context)
                    btnUpdate.text = "Update"
                    btnUpdate.setBackgroundResource(R.drawable.btn_background)
                    btnUpdate.setTextColor(resources.getColor(R.color.white,null))
                    btnUpdate.layoutParams = btnLayoutParams

                    btnUpdate.setOnClickListener{
                        var userNameBundle = Bundle()
                        userNameBundle.putString("userName", username)
                        userNameBundle.putString("roomID", room?.roomName.toString().replace(" ",""))

                        val updateRoomFragment = HotelRoomUpdateFragment()
                        updateRoomFragment.arguments = userNameBundle
                        parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel, updateRoomFragment).commit()

                    }

                   /* val btnDelete = Button(context)
                    btnDelete.text = "Delete"
                    btnDelete.setBackgroundResource(R.drawable.btn_background)
                    btnDelete.setTextColor(resources.getColor(R.color.white,null))
                    btnDelete.layoutParams = btnLayoutParams

                    btnDelete.setOnClickListener{
                        Toast.makeText(context, "Delete Button of ${room!!.roomName}", Toast.LENGTH_SHORT).show()
                        val roomID = room?.roomName.toString().replace(" ","")
                        if(roomID.isNotEmpty()){
                            deleteRoom(roomID, username.toString())
                        }
                        else{
                            Toast.makeText(context, "Error Occurred Cannot Delete!", Toast.LENGTH_SHORT).show()
                        }
                    }*/

                    val btnMore = Button(context)
                    btnMore.text = "More"
                    btnMore.setBackgroundResource(R.drawable.btn_background)
                    btnMore.setTextColor(resources.getColor(R.color.white,null))
                    btnMore.layoutParams = btnLayoutParams

                    btnMore.setOnClickListener{
                        val popupView = layoutInflater.inflate(R.layout.layout_popup, null)

                        val popupLinearLayout = popupView.findViewById<LinearLayout>(R.id.popupLinearLayout)

                        val tvPopup = popupView.findViewById<TextView>(R.id.tvPopup)
                        tvPopup.setText(room!!.roomName.toString())

                        val btnClose = popupView.findViewById<ImageButton>(R.id.closeBtn)

                        val popupWindow = PopupWindow(popupView,
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT)
                        popupWindow.isFocusable = false

                        popupWindow.showAtLocation(container, Gravity.CENTER, 0, 0)

                        container.addView(overlayView)

                        btnClose.setOnClickListener{
                            container.removeView(overlayView)
                            popupWindow.dismiss()
                        }



                        val btnPopupLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                        btnPopupLayoutParams.setMargins(10,10,10,15)

                        val btnDelete = Button(context)
                        btnDelete.text = "Delete"
                        btnDelete.setBackgroundResource(R.drawable.btn_background)
                        btnDelete.setTextColor(resources.getColor(R.color.white,null))
                        btnDelete.layoutParams = btnPopupLayoutParams

                        btnDelete.setOnClickListener{
                            Toast.makeText(context, "Delete Button of ${room!!.roomName}", Toast.LENGTH_SHORT).show()
                            val roomID = room?.roomName.toString().replace(" ","")
                            if(roomID.isNotEmpty()){
                                deleteRoom(roomID, username.toString())
                                container.removeView(overlayView)
                                popupWindow.dismiss()
                            }
                            else{
                                Toast.makeText(context, "Error Occurred Cannot Delete!", Toast.LENGTH_SHORT).show()
                            }
                        }


                        val tvDescription = TextView(context)
                        tvDescription.text = "Description:"
                        tvDescription.textSize = 20f
                        tvDescription.setTypeface(null, Typeface.BOLD)
                        val tvRoomDes = TextView(context)
                        tvRoomDes.text = room!!.roomDescription
                        tvRoomDes.textSize = 15f
                        tvRoomDes.setPadding(10,0,0,15)

                        val tvPrice = TextView(context)
                        tvPrice.text = "Price:"
                        tvPrice.textSize = 20f
                        tvPrice.setTypeface(null, Typeface.BOLD)
                        val tvRoomPrice = TextView(context)
                        tvRoomPrice.text = "${room!!.roomPrice}"
                        tvRoomPrice.textSize = 18f
                        tvRoomPrice.setPadding(10,0,0,25)

                        val tvServedFor = TextView(context)
                        tvServedFor.text = "Served For:"
                        tvServedFor.textSize = 20f
                        tvServedFor.setTypeface(null, Typeface.BOLD)
                        val tvRoomServedFor = TextView(context)
                        tvRoomServedFor.text = "${room!!.roomFor}"
                        tvRoomServedFor.textSize = 18f
                        tvRoomServedFor.setPadding(10,0,0,25)

                        val tvPayFor = TextView(context)
                        tvPayFor.text = "Pay For:"
                        tvPayFor.textSize = 20f
                        tvPayFor.setTypeface(null, Typeface.BOLD)
                        val tvRoomPayFor = TextView(context)
                        tvRoomPayFor.text = "${room!!.roomPayFor}"
                        tvRoomPayFor.textSize = 18f
                        tvRoomPayFor.setPadding(10,0,0,25)

                        val tvConditions = TextView(context)
                        tvConditions.text = "Conditions:"
                        tvConditions.textSize = 20f
                        tvConditions.setTypeface(null, Typeface.BOLD)
                        var conditions=""
                        //Served for time
                        if(room!!.ac == true){
                            conditions += "A/C\n"
                        }
                        if(room!!.fan == true){
                            conditions += "Fan\n"
                        }
                        if(room!!.wiFi == true){
                            conditions += "Wi-Fi\n"
                        }
                        if(room!!.tv == true){
                            conditions = conditions+"TV\n"
                        }
                        if(room!!.hotWater == true){
                            conditions += "Hot Water\n"
                        }
                        if(room!!.balcony == true){
                            conditions += "Balcony\n"
                        }
                        val tvRoomConditions = TextView(context)
                        tvRoomConditions.text = conditions
                        tvRoomConditions.textSize = 18f
                        tvRoomConditions.setPadding(10,0,0,25)

                        popupLinearLayout.addView(tvDescription)
                        popupLinearLayout.addView(tvRoomDes)
                        popupLinearLayout.addView(tvPrice)
                        popupLinearLayout.addView(tvRoomPrice)
                        popupLinearLayout.addView(tvServedFor)
                        popupLinearLayout.addView(tvRoomServedFor)
                        popupLinearLayout.addView(tvPayFor)
                        popupLinearLayout.addView(tvRoomPayFor)
                        popupLinearLayout.addView(tvConditions)
                        popupLinearLayout.addView(tvRoomConditions)
                        popupLinearLayout.addView(btnDelete)
                    }

                    linearLayoutInner.addView(tvRoomName)
                    linearLayoutInner.addView(tvRoomDes)
                    linearLayoutInner.addView(tvRoomPrice)
                    linearLayoutInner.addView(btnUpdate)
                    //linearLayoutInner.addView(btnDelete)
                    linearLayoutInner.addView(btnMore)

                    linearLayout.addView(linearLayoutInner)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        return view
    }

    private fun deleteRoom(roomID: String, userName: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("hotel/$userName/hotelRooms")
        databaseReference.child(roomID).removeValue().addOnSuccessListener {
            var userNameBundle = Bundle()
            userNameBundle.putString("userName", userName)

            val allRoomFragment = HotelRoomMealPackageDisplayFragment()
            allRoomFragment.arguments = userNameBundle
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel, allRoomFragment).commit()
        Toast.makeText(context, "Room Data was Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {

        }
    }
}