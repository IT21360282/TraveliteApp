package com.example.tourismhelper

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.tourismhelper.database.HotelBooking
import com.example.tourismhelper.database.HotelMealData
import com.example.tourismhelper.database.HotelRoomData
import com.example.tourismhelper.reshotelfragment.ResHotelIncomeFragment
import com.google.firebase.database.*

class HotelProfileViewActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var phone: String
    private lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_profile_view)

        var username = intent.getStringExtra("userName")

        var hotelProfImg = findViewById<ImageView>(R.id.hotelProfImg)

        var hotelName = findViewById<TextView>(R.id.tvHotelName)
        var starRate = findViewById<TextView>(R.id.tvStarRate)

        var btnBooking = findViewById<Button>(R.id.btnBooking)
        var btnViewRooms = findViewById<Button>(R.id.btnViewRooms)
        var btnViewPackages = findViewById<Button>(R.id.btnViewPackages)

        var about = findViewById<TextView>(R.id.hotelAbout)
        var address = findViewById<TextView>(R.id.hotelAddress)

        databaseReference = FirebaseDatabase.getInstance().getReference("hotel")
        databaseReference.child(username.toString()).get().addOnSuccessListener {
            if(it.exists()){
                var hotelNameFromDB = it.child("hotelName").value.toString()
                var hotelDescriptionFromDB = it.child("hotelDescription").value.toString()
                var bEmailFromDB = it.child("businessEmail").value.toString()
                var bPhoneFromDB = it.child("businessPhone").value.toString()
                var bLocationFromDB = it.child("businessLocation").value.toString()
                var starRateFromDB = it.child("starRate").value.toString()

                var generatedAbout = "$bEmailFromDB\n\n$bPhoneFromDB\n\n$hotelDescriptionFromDB"

                phone = bPhoneFromDB
                email = bEmailFromDB

                hotelName.setText("$hotelNameFromDB")
                starRate.setText("$starRateFromDB")
                about.setText("$generatedAbout")
                address.setText("$bLocationFromDB")
            }
            else{
                //Toast.makeText(this, "User Does not Exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{

        }

        //popup overlay
        val overlayView = View(this)
        overlayView.setBackgroundResource(R.drawable.popup_overlay)
        overlayView.alpha = 1f
        overlayView.isClickable = false
        val container = findViewById<FrameLayout>(R.id.popupContainer)

        btnBooking.setOnClickListener {
            val popupView = layoutInflater.inflate(R.layout.layout_popup, null)

            val popupLinearLayout = popupView.findViewById<LinearLayout>(R.id.popupLinearLayout)

            val tvPopup = popupView.findViewById<TextView>(R.id.tvPopup)
            tvPopup.setText("Book '${hotelName.text.toString()}'")

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

            val btnBook = Button(this)
            btnBook.text = "Book"
            btnBook.setBackgroundResource(R.drawable.btn_background)
            btnBook.setTextColor(resources.getColor(R.color.white,null))
            btnBook.layoutParams = btnPopupLayoutParams

            btnBook.setOnClickListener {

                popupWindow.dismiss()

                val popupViewNext = layoutInflater.inflate(R.layout.layout_popup, null)

                val popupLinearLayout = popupView.findViewById<LinearLayout>(R.id.popupLinearLayout)

                val tvPopupNext = popupViewNext.findViewById<TextView>(R.id.tvPopup)
                tvPopupNext.setText("Test")

                val btnCloseNext = popupViewNext.findViewById<ImageButton>(R.id.closeBtn)

                val popupWindowNext = PopupWindow(popupViewNext,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
                popupWindowNext.isFocusable = false

                popupWindowNext.showAtLocation(container, Gravity.CENTER, 0, 0)

                btnCloseNext.setOnClickListener{
                    container.removeView(overlayView)
                    popupWindowNext.dismiss()
                }
            }


            val phoneTxt = TextView(this)
            phoneTxt.text = "Contact Number:"
            phoneTxt.textSize = 20f
            phoneTxt.setTypeface(null, Typeface.BOLD)
            val phoneValue = TextView(this)
            phoneValue.text = phone
            phoneValue.textSize = 15f
            phoneValue.setPadding(10,0,0,15)

            val emailTxt = TextView(this)
            emailTxt.text = "Email Address:"
            emailTxt.textSize = 20f
            emailTxt.setTypeface(null, Typeface.BOLD)
            val emailValue = TextView(this)
            emailValue.text = email
            emailValue.textSize = 15f
            emailValue.setPadding(10,0,0,15)

            val addressTxt = TextView(this)
            addressTxt.text = "Address:"
            addressTxt.textSize = 20f
            addressTxt.setTypeface(null, Typeface.BOLD)
            val addressValue = TextView(this)
            addressValue.text = address.text.toString()
            addressValue.textSize = 15f
            addressValue.setPadding(10,0,0,15)

            popupLinearLayout.addView(phoneTxt)
            popupLinearLayout.addView(phoneValue)
            popupLinearLayout.addView(emailTxt)
            popupLinearLayout.addView(emailValue)
            popupLinearLayout.addView(addressTxt)
            popupLinearLayout.addView(addressValue)
            popupLinearLayout.addView(btnBook)
        }

        btnViewRooms.setOnClickListener {
            val popupView = layoutInflater.inflate(R.layout.layout_popup, null)

            val popupLinearLayout = popupView.findViewById<LinearLayout>(R.id.popupLinearLayout)

            val tvPopup = popupView.findViewById<TextView>(R.id.tvPopup)
            tvPopup.setText("Rooms of '${hotelName.text.toString()}'")

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

            val database = FirebaseDatabase.getInstance()
            val usersRef = database.getReference("hotel/$username/hotelRooms")

            usersRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (userSnapshot in dataSnapshot.children) {
                        val linearLayoutInner = LinearLayout(popupView.context)
                        linearLayoutInner.orientation = LinearLayout.VERTICAL
                        linearLayoutInner.setBackgroundResource(R.drawable.styles_edit_text)

                        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                        layoutParams.setMargins(50, 0, 50, 30)
                        linearLayoutInner.layoutParams = layoutParams
                        linearLayoutInner.gravity =Gravity.CENTER

                        linearLayoutInner.setPadding(40,30,40,30)

                        val room = userSnapshot.getValue(HotelRoomData::class.java)

                        val tvRoomName = TextView(popupView.context)
                        tvRoomName.text = room!!.roomName
                        tvRoomName.setTextColor(resources.getColor(R.color.main_color,null))
                        tvRoomName.textSize = 22f
                        tvRoomName.setTypeface(null,Typeface.BOLD)
                        tvRoomName.setPadding(0,0,0,10)

                        val tvRoomDes = TextView(popupView.context)
                        tvRoomDes.text = room!!.roomDescription
                        tvRoomDes.textSize = 15f
                        tvRoomDes.setPadding(0,0,0,15)

                        val tvRoomPrice = TextView(popupView.context)
                        tvRoomPrice.text = "Price: ${room!!.roomPrice}"
                        tvRoomPrice.textSize = 18f
                        tvRoomPrice.setPadding(0,0,0,25)

                        val btnLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                        btnLayoutParams.setMargins(0,0,0,15)

                        val btnMore = Button(popupView.context)
                        btnMore.text = "More"
                        btnMore.setBackgroundResource(R.drawable.btn_background)
                        btnMore.setTextColor(resources.getColor(R.color.white,null))
                        btnMore.layoutParams = btnLayoutParams

                        btnMore.setOnClickListener{
                            popupWindow.dismiss()

                            val popupViewNext = layoutInflater.inflate(R.layout.layout_popup, null)

                            val popupLinearLayoutNext = popupViewNext.findViewById<LinearLayout>(R.id.popupLinearLayout)

                            val tvPopupNext = popupViewNext.findViewById<TextView>(R.id.tvPopup)
                            tvPopupNext.setText(room?.roomName.toString())

                            val btnCloseNext = popupViewNext.findViewById<ImageButton>(R.id.closeBtn)

                            val popupWindowNext = PopupWindow(popupViewNext,
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT)
                            popupWindowNext.isFocusable = false

                            popupWindowNext.showAtLocation(container, Gravity.CENTER, 0, 0)

                            btnCloseNext.setOnClickListener{
                                container.removeView(overlayView)
                                popupWindowNext.dismiss()
                            }



                            val btnPopupLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                            btnPopupLayoutParams.setMargins(10,10,10,15)

                            val btnBookInRoom = Button(popupViewNext.context)
                            btnBookInRoom.text = "Book"
                            btnBookInRoom.setBackgroundResource(R.drawable.btn_background)
                            btnBookInRoom.setTextColor(resources.getColor(R.color.white,null))
                            btnBookInRoom.layoutParams = btnPopupLayoutParams

                            btnBookInRoom.setOnClickListener{
                                val roomID = room?.roomName.toString().replace(" ","")
                                databaseReference =
                                    FirebaseDatabase.getInstance().getReference("hotel/$username/hotelBooking")
                                val booking = HotelBooking(
                                    "user1",
                                    room?.roomName.toString()
                                )
                                databaseReference.child(roomID).setValue(booking).addOnSuccessListener {
                                    Toast.makeText(popupViewNext.context, "Room is Booked Successfully", Toast.LENGTH_SHORT).show()

                                    container.removeView(overlayView)
                                    popupWindowNext.dismiss()

                                }.addOnFailureListener {
                                    Toast.makeText(popupViewNext.context, "Booking Failed", Toast.LENGTH_SHORT).show()
                                }
                            }


                            val tvDescription = TextView(popupViewNext.context)
                            tvDescription.text = "Description:"
                            tvDescription.textSize = 20f
                            tvDescription.setTypeface(null, Typeface.BOLD)
                            val tvRoomDes = TextView(popupViewNext.context)
                            tvRoomDes.text = room!!.roomDescription
                            tvRoomDes.textSize = 15f
                            tvRoomDes.setPadding(10,0,0,15)

                            val tvPrice = TextView(popupViewNext.context)
                            tvPrice.text = "Price:"
                            tvPrice.textSize = 20f
                            tvPrice.setTypeface(null, Typeface.BOLD)
                            val tvRoomPrice = TextView(popupViewNext.context)
                            tvRoomPrice.text = "${room!!.roomPrice}"
                            tvRoomPrice.textSize = 18f
                            tvRoomPrice.setPadding(10,0,0,25)

                            val tvServedFor = TextView(popupViewNext.context)
                            tvServedFor.text = "Served For:"
                            tvServedFor.textSize = 20f
                            tvServedFor.setTypeface(null, Typeface.BOLD)
                            val tvRoomServedFor = TextView(popupViewNext.context)
                            tvRoomServedFor.text = "${room!!.roomFor}"
                            tvRoomServedFor.textSize = 18f
                            tvRoomServedFor.setPadding(10,0,0,25)

                            val tvPayFor = TextView(popupViewNext.context)
                            tvPayFor.text = "Pay For:"
                            tvPayFor.textSize = 20f
                            tvPayFor.setTypeface(null, Typeface.BOLD)
                            val tvRoomPayFor = TextView(popupViewNext.context)
                            tvRoomPayFor.text = "${room!!.roomPayFor}"
                            tvRoomPayFor.textSize = 18f
                            tvRoomPayFor.setPadding(10,0,0,25)

                            val tvConditions = TextView(popupViewNext.context)
                            tvConditions.text = "Conditions:"
                            tvConditions.textSize = 20f
                            tvConditions.setTypeface(null, Typeface.BOLD)
                            var conditions=""
                            //Served for time
                            if(room!!.ac == true){
                                conditions = conditions+"A/C\n"
                            }
                            if(room!!.fan == true){
                                conditions = conditions+"Fan\n"
                            }
                            if(room!!.wiFi == true){
                                conditions = conditions+"Wi-Fi\n"
                            }
                            if(room!!.tv == true){
                                conditions = conditions+"TV\n"
                            }
                            if(room!!.hotWater == true){
                                conditions = conditions+"Hot Water\n"
                            }
                            if(room!!.balcony == true){
                                conditions = conditions+"Balcony\n"
                            }
                            val tvRoomConditions = TextView(popupViewNext.context)
                            tvRoomConditions.text = conditions
                            tvRoomConditions.textSize = 18f
                            tvRoomConditions.setPadding(10,0,0,25)

                            popupLinearLayoutNext.addView(tvDescription)
                            popupLinearLayoutNext.addView(tvRoomDes)
                            popupLinearLayoutNext.addView(tvPrice)
                            popupLinearLayoutNext.addView(tvRoomPrice)
                            popupLinearLayoutNext.addView(tvServedFor)
                            popupLinearLayoutNext.addView(tvRoomServedFor)
                            popupLinearLayoutNext.addView(tvPayFor)
                            popupLinearLayoutNext.addView(tvRoomPayFor)
                            popupLinearLayoutNext.addView(tvConditions)
                            popupLinearLayoutNext.addView(tvRoomConditions)
                            popupLinearLayoutNext.addView(btnBookInRoom)
                        }

                        linearLayoutInner.addView(tvRoomName)
                        linearLayoutInner.addView(tvRoomDes)
                        linearLayoutInner.addView(tvRoomPrice)
                        linearLayoutInner.addView(btnMore)

                        popupLinearLayout.addView(linearLayoutInner)

                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {


                }
            })
        }
    }
}