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
import com.example.tourismhelper.database.HotelMealData
import com.example.tourismhelper.database.RestaurantComboData
import com.google.firebase.database.*

class RestaurantComboPackDisplayFragment : Fragment() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_restaurant_combo_pack_display, container, false)

        //get userName from parent activity (Hotel Main Activity)
        var username = arguments?.getString("userName")

        val linearLayout = view.findViewById<LinearLayout>(R.id.displayComboContainer)

        //popup overlay
        val overlayView = View(requireContext())
        overlayView.setBackgroundResource(R.drawable.popup_overlay)
        overlayView.alpha = 1f
        overlayView.isClickable = false
        val container = view.findViewById<FrameLayout>(R.id.popupContainer)

        //get all meal's data from database
        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("restaurant/$username/restaurantCombo")

        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (userSnapshot in dataSnapshot.children) {
                    val linearLayoutInner = LinearLayout(context)
                    linearLayoutInner.orientation = LinearLayout.VERTICAL
                    linearLayoutInner.setBackgroundResource(R.drawable.styles_linear_layout)

                    val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    layoutParams.setMargins(50, 0, 50, 30)
                    linearLayoutInner.layoutParams = layoutParams
                    linearLayoutInner.gravity = Gravity.CENTER

                    linearLayoutInner.setPadding(40,30,40,30)

                    val combo = userSnapshot.getValue(RestaurantComboData::class.java)

                    val tvComboName = TextView(context)
                    tvComboName.text = combo!!.comboName
                    tvComboName.setTextColor(resources.getColor(R.color.main_color,null))
                    tvComboName.textSize = 22f
                    tvComboName.setTypeface(null, Typeface.BOLD)
                    tvComboName.setPadding(0,0,0,10)

                    val btnLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    btnLayoutParams.setMargins(0,0,0,15)

                    val tvComboDes = TextView(context)
                    tvComboDes.text = combo!!.comboDescription
                    tvComboDes.textSize = 15f
                    tvComboDes.setPadding(0,0,0,15)

                    val tvComboPrice = TextView(context)
                    tvComboPrice.text = "Price: ${combo!!.comboPrice}"
                    tvComboPrice.textSize = 18f
                    tvComboPrice.setPadding(0,0,0,25)

                    val btnUpdate = Button(context)
                    btnUpdate.text = "Update"
                    btnUpdate.setBackgroundResource(R.drawable.btn_background)
                    btnUpdate.setTextColor(resources.getColor(R.color.white,null))
                    btnUpdate.layoutParams = btnLayoutParams

                    btnUpdate.setOnClickListener{


                    }

                    val btnMore = Button(context)
                    btnMore.text = "More"
                    btnMore.setBackgroundResource(R.drawable.btn_background)
                    btnMore.setTextColor(resources.getColor(R.color.white,null))
                    btnMore.layoutParams = btnLayoutParams

                    btnMore.setOnClickListener{
                        val popupView = layoutInflater.inflate(R.layout.layout_popup, null)

                        val popupLinearLayout = popupView.findViewById<LinearLayout>(R.id.popupLinearLayout)

                        val tvPopup = popupView.findViewById<TextView>(R.id.tvPopup)
                        tvPopup.setText(combo!!.comboName.toString())

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
                            val comboID = combo?.comboName.toString().replace(" ","")
                            if(comboID.isNotEmpty()){
                                deleteCombo(comboID, username.toString())
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
                        val tvComboDes = TextView(context)
                        tvComboDes.text = combo!!.comboDescription
                        tvComboDes.textSize = 15f
                        tvComboDes.setPadding(10,0,0,15)

                        val tvPrice = TextView(context)
                        tvPrice.text = "Price:"
                        tvPrice.textSize = 20f
                        tvPrice.setTypeface(null, Typeface.BOLD)
                        val tvComboPrice = TextView(context)
                        tvComboPrice.text = "${combo!!.comboPrice}"
                        tvComboPrice.textSize = 18f
                        tvComboPrice.setPadding(10,0,0,25)

                        val tvServedFor = TextView(context)
                        tvServedFor.text = "Served For:"
                        tvServedFor.textSize = 20f
                        tvServedFor.setTypeface(null, Typeface.BOLD)
                        val tvComboServedFor = TextView(context)
                        tvComboServedFor.text = "${combo!!.comboServedFor}"
                        tvComboServedFor.textSize = 18f
                        tvComboServedFor.setPadding(10,0,0,25)

                        val tvIncludingItem = TextView(context)
                        tvIncludingItem.text = "Including Items:"
                        tvIncludingItem.textSize = 20f
                        tvIncludingItem.setTypeface(null, Typeface.BOLD)
                        val tvComboIncludingItems = TextView(context)
                        tvComboIncludingItems.text = "${combo!!.comboIncludingItems}"
                        tvComboIncludingItems.textSize = 18f
                        tvComboIncludingItems.setPadding(10,0,0,25)

                        val tvIncludingFreeItem = TextView(context)
                        tvIncludingFreeItem.text = "Including Free Items:"
                        tvIncludingFreeItem.textSize = 20f
                        tvIncludingFreeItem.setTypeface(null, Typeface.BOLD)
                        val tvComboIncludingFreeItems = TextView(context)
                        tvComboIncludingFreeItems.text = "${combo!!.comboIncludingFreeItems}"
                        tvComboIncludingFreeItems.textSize = 18f
                        tvComboIncludingFreeItems.setPadding(10,0,0,25)



                        popupLinearLayout.addView(tvDescription)
                        popupLinearLayout.addView(tvComboDes)
                        popupLinearLayout.addView(tvPrice)
                        popupLinearLayout.addView(tvComboPrice)
                        popupLinearLayout.addView(tvServedFor)
                        popupLinearLayout.addView(tvComboServedFor)
                        popupLinearLayout.addView(tvIncludingItem)
                        popupLinearLayout.addView(tvComboIncludingItems)
                        popupLinearLayout.addView(tvIncludingFreeItem)
                        popupLinearLayout.addView(tvComboIncludingFreeItems)
                        popupLinearLayout.addView(btnDelete)
                    }

                    linearLayoutInner.addView(tvComboName)
                    linearLayoutInner.addView(tvComboDes)
                    linearLayoutInner.addView(tvComboPrice)
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

    private fun deleteCombo(comboID: String, userName: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("restaurant/$userName/restaurantCombo")
        databaseReference.child(comboID).removeValue().addOnSuccessListener {
            var userNameBundle = Bundle()
            userNameBundle.putString("userName", userName)

            val allComboFragment = RestaurantComboPackDisplayFragment()
            allComboFragment.arguments = userNameBundle
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel, allComboFragment).commit()
            Toast.makeText(context, "Meal Data was Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {

        }
    }
}