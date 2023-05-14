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
import com.google.firebase.database.*

class RestaurantMealDisplayFragment : Fragment() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_restaurant_meal_display, container, false)

        //get userName from parent activity (Hotel Main Activity)
        var username = arguments?.getString("userName")

        val linearLayout = view.findViewById<LinearLayout>(R.id.displayMealContainer)

        //popup overlay
        val overlayView = View(requireContext())
        overlayView.setBackgroundResource(R.drawable.popup_overlay)
        overlayView.alpha = 1f
        overlayView.isClickable = false
        val container = view.findViewById<FrameLayout>(R.id.popupContainer)

        //get all meal's data from database
        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("restaurant/$username/restaurantMeals")

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

                    val meal = userSnapshot.getValue(HotelMealData::class.java)

                    val tvMealName = TextView(context)
                    tvMealName.text = meal!!.mealName
                    tvMealName.setTextColor(resources.getColor(R.color.main_color,null))
                    tvMealName.textSize = 22f
                    tvMealName.setTypeface(null, Typeface.BOLD)
                    tvMealName.setPadding(0,0,0,10)

                    val btnLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    btnLayoutParams.setMargins(0,0,0,15)

                    val tvMealDes = TextView(context)
                    tvMealDes.text = meal!!.mealDescription
                    tvMealDes.textSize = 15f
                    tvMealDes.setPadding(0,0,0,15)

                    val tvMealPrice = TextView(context)
                    tvMealPrice.text = "Price: ${meal!!.mealPrice}"
                    tvMealPrice.textSize = 18f
                    tvMealPrice.setPadding(0,0,0,25)

                    val btnUpdate = Button(context)
                    btnUpdate.text = "Update"
                    btnUpdate.setBackgroundResource(R.drawable.btn_background)
                    btnUpdate.setTextColor(resources.getColor(R.color.white,null))
                    btnUpdate.layoutParams = btnLayoutParams

                    btnUpdate.setOnClickListener{
                        Toast.makeText(context, "Update Button of ${meal!!.mealName}", Toast.LENGTH_SHORT).show()
                    }

                    /*val btnDelete = Button(context)
                    btnDelete.text = "Delete"
                    btnDelete.setBackgroundResource(R.drawable.btn_background)
                    btnDelete.setTextColor(resources.getColor(R.color.white,null))
                    btnDelete.layoutParams = btnLayoutParams

                    btnDelete.setOnClickListener{
                        Toast.makeText(context, "Delete Button of ${meal!!.mealName}", Toast.LENGTH_SHORT).show()
                        val mealID = meal?.mealName.toString().replace(" ","")
                        if(mealID.isNotEmpty()){
                            deleteMeal(mealID, username.toString())
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
                        tvPopup.setText(meal!!.mealName.toString())

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
                            Toast.makeText(context, "Delete Button of ${meal!!.mealName}", Toast.LENGTH_SHORT).show()
                            val mealID = meal?.mealName.toString().replace(" ","")
                            if(mealID.isNotEmpty()){
                                deleteMeal(mealID, username.toString())
                                container.removeView(overlayView)
                                popupWindow.dismiss()
                            }
                            else{
                                Toast.makeText(context, "Error Occurred Cannot Delete!", Toast.LENGTH_SHORT).show()
                            }
                        }

                        val tvMealName = TextView(context)
                        tvMealName.text = meal!!.mealName
                        tvMealName.setTextColor(resources.getColor(R.color.main_color,null))
                        tvMealName.textSize = 22f
                        tvMealName.setTypeface(null, Typeface.BOLD)
                        tvMealName.setPadding(0,0,0,10)

                        val tvDescription = TextView(context)
                        tvDescription.text = "Description:"
                        tvDescription.textSize = 20f
                        tvDescription.setTypeface(null, Typeface.BOLD)
                        val tvMealDes = TextView(context)
                        tvMealDes.text = meal!!.mealDescription
                        tvMealDes.textSize = 15f
                        tvMealDes.setPadding(10,0,0,15)

                        val tvPrice = TextView(context)
                        tvPrice.text = "Price:"
                        tvPrice.textSize = 20f
                        tvPrice.setTypeface(null, Typeface.BOLD)
                        val tvMealPrice = TextView(context)
                        tvMealPrice.text = "${meal!!.mealPrice}"
                        tvMealPrice.textSize = 18f
                        tvMealPrice.setPadding(10,0,0,25)

                        val tvServedFor = TextView(context)
                        tvServedFor.text = "Served For:"
                        tvServedFor.textSize = 20f
                        tvServedFor.setTypeface(null, Typeface.BOLD)
                        val tvMealServedFor = TextView(context)
                        tvMealServedFor.text = "${meal!!.mealServedFor}"
                        tvMealServedFor.textSize = 18f
                        tvMealServedFor.setPadding(10,0,0,25)

                        val tvIncludingItem = TextView(context)
                        tvIncludingItem.text = "Including Items:"
                        tvIncludingItem.textSize = 20f
                        tvIncludingItem.setTypeface(null, Typeface.BOLD)
                        val tvMealIncludingItems = TextView(context)
                        tvMealIncludingItems.text = "${meal!!.mealIncludingItems}"
                        tvMealIncludingItems.textSize = 18f
                        tvMealIncludingItems.setPadding(10,0,0,25)

                        val tvServedForTime = TextView(context)
                        tvServedForTime.text = "Served For:"
                        tvServedForTime.textSize = 20f
                        tvServedForTime.setTypeface(null, Typeface.BOLD)
                        var servedForTime=""
                        //Served for time
                        if(meal!!.breakfirst == true){
                            servedForTime = servedForTime+"Breakfast\n"
                        }
                        if(meal!!.lunch == true){
                            servedForTime = servedForTime+"Lunch\n"
                        }
                        if(meal!!.teaTime == true){
                            servedForTime = servedForTime+"Tea Time\n"
                        }
                        if(meal!!.dinner == true){
                            servedForTime = servedForTime+"Dinner\n"
                        }
                        val tvMealServedForTime = TextView(context)
                        tvMealServedForTime.text = servedForTime
                        tvMealServedForTime.textSize = 18f
                        tvMealServedForTime.setPadding(10,0,0,25)

                        popupLinearLayout.addView(tvDescription)
                        popupLinearLayout.addView(tvMealDes)
                        popupLinearLayout.addView(tvPrice)
                        popupLinearLayout.addView(tvMealPrice)
                        popupLinearLayout.addView(tvServedFor)
                        popupLinearLayout.addView(tvMealServedFor)
                        popupLinearLayout.addView(tvIncludingItem)
                        popupLinearLayout.addView(tvMealIncludingItems)
                        popupLinearLayout.addView(tvServedForTime)
                        popupLinearLayout.addView(tvMealServedForTime)
                        popupLinearLayout.addView(btnDelete)
                    }

                    linearLayoutInner.addView(tvMealName)
                    linearLayoutInner.addView(tvMealDes)
                    linearLayoutInner.addView(tvMealPrice)
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

    private fun deleteMeal(mealID: String, userName: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("restaurant/$userName/restaurantMeals")
        databaseReference.child(mealID).removeValue().addOnSuccessListener {
            var userNameBundle = Bundle()
            userNameBundle.putString("userName", userName)

            val allMealFragment = RestaurantMealDisplayFragment()
            allMealFragment.arguments = userNameBundle
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel, allMealFragment).commit()
            Toast.makeText(context, "Meal Data was Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {

        }
    }
}