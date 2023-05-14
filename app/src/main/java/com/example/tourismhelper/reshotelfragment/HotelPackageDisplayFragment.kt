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
import com.example.tourismhelper.database.HotelPackageData
import com.google.firebase.database.*

class HotelPackageDisplayFragment : Fragment() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hotel_package_display, container, false)

        var username = arguments?.getString("userName")

        val linearLayout = view.findViewById<LinearLayout>(R.id.displayPackageContainer)

        //popup overlay
        val overlayView = View(requireContext())
        overlayView.setBackgroundResource(R.drawable.popup_overlay)
        overlayView.alpha = 1f
        overlayView.isClickable = false
        val container = view.findViewById<FrameLayout>(R.id.popupPackageContainer)

        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("hotel/$username/hotelPackages")

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

                    val pack = userSnapshot.getValue(HotelPackageData::class.java)

                    val tvPackageName = TextView(context)
                    tvPackageName.text = pack!!.packageName
                    tvPackageName.setTextColor(resources.getColor(R.color.main_color,null))
                    tvPackageName.textSize = 22f
                    tvPackageName.setTypeface(null, Typeface.BOLD)
                    tvPackageName.setPadding(0,0,0,10)

                    val btnLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    btnLayoutParams.setMargins(0,0,0,15)

                    val tvPackageDes = TextView(context)
                    tvPackageDes.text = pack!!.packageDescription
                    tvPackageDes.textSize = 15f
                    tvPackageDes.setPadding(0,0,0,15)

                    val tvPackagePrice = TextView(context)
                    tvPackagePrice.text = "Price: ${pack!!.packagePrice}"
                    tvPackagePrice.textSize = 18f
                    tvPackagePrice.setPadding(0,0,0,25)

                    val btnUpdate = Button(context)
                    btnUpdate.text = "Update"
                    btnUpdate.setBackgroundResource(R.drawable.btn_background)
                    btnUpdate.setTextColor(resources.getColor(R.color.white,null))
                    btnUpdate.layoutParams = btnLayoutParams

                    btnUpdate.setOnClickListener{
                        Toast.makeText(context, "Update Button of ${pack!!.packageName}", Toast.LENGTH_SHORT).show()
                    }

                    /*val btnDelete = Button(context)
                    btnDelete.text = "Delete"
                    btnDelete.setBackgroundResource(R.drawable.btn_background)
                    btnDelete.setTextColor(resources.getColor(R.color.white,null))
                    btnDelete.layoutParams = btnLayoutParams

                    btnDelete.setOnClickListener{
                        val packageID = pack?.packageName.toString().replace(" ","")
                        if(packageID.isNotEmpty()){
                            deletePackage(packageID, username.toString())
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
                        tvPopup.setText(pack!!.packageName.toString())

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
                            val packageID = pack?.packageName.toString().replace(" ","")
                            if(packageID.isNotEmpty()){
                                deletePackage(packageID, username.toString())
                                container.removeView(overlayView)
                                popupWindow.dismiss()
                            }
                            else{
                                Toast.makeText(context, "Error Occurred Cannot Delete!", Toast.LENGTH_SHORT).show()
                            }
                        }

                        val tvMealName = TextView(context)
                        tvMealName.text = pack!!.packageName
                        tvMealName.setTextColor(resources.getColor(R.color.main_color,null))
                        tvMealName.textSize = 22f
                        tvMealName.setTypeface(null, Typeface.BOLD)
                        tvMealName.setPadding(0,0,0,10)

                        val tvDescription = TextView(context)
                        tvDescription.text = "Description:"
                        tvDescription.textSize = 20f
                        tvDescription.setTypeface(null, Typeface.BOLD)
                        val tvPackageDes = TextView(context)
                        tvPackageDes.text = pack!!.packageDescription
                        tvPackageDes.textSize = 15f
                        tvPackageDes.setPadding(10,0,0,15)

                        val tvPrice = TextView(context)
                        tvPrice.text = "Price:"
                        tvPrice.textSize = 20f
                        tvPrice.setTypeface(null, Typeface.BOLD)
                        val tvPackagePrice = TextView(context)
                        tvPackagePrice.text = "${pack!!.packagePrice}"
                        tvPackagePrice.textSize = 18f
                        tvPackagePrice.setPadding(10,0,0,25)

                        val tvServedFor = TextView(context)
                        tvServedFor.text = "Served For:"
                        tvServedFor.textSize = 20f
                        tvServedFor.setTypeface(null, Typeface.BOLD)
                        val tvPackageServedFor = TextView(context)
                        tvPackageServedFor.text = "${pack!!.packageServedFor}"
                        tvPackageServedFor.textSize = 18f
                        tvPackageServedFor.setPadding(10,0,0,25)

                        val tvIncludingItem = TextView(context)
                        tvIncludingItem.text = "Including Items:"
                        tvIncludingItem.textSize = 20f
                        tvIncludingItem.setTypeface(null, Typeface.BOLD)
                        val tvPackageIncludingItems = TextView(context)
                        tvPackageIncludingItems.text = "${pack!!.packageIncludingItems}"
                        tvPackageIncludingItems.textSize = 18f
                        tvPackageIncludingItems.setPadding(10,0,0,25)


                        popupLinearLayout.addView(tvDescription)
                        popupLinearLayout.addView(tvPackageDes)
                        popupLinearLayout.addView(tvPrice)
                        popupLinearLayout.addView(tvPackagePrice)
                        popupLinearLayout.addView(tvServedFor)
                        popupLinearLayout.addView(tvPackageServedFor)
                        popupLinearLayout.addView(tvIncludingItem)
                        popupLinearLayout.addView(tvPackageIncludingItems)
                        popupLinearLayout.addView(btnDelete)
                    }

                    linearLayoutInner.addView(tvPackageName)
                    linearLayoutInner.addView(tvPackageDes)
                    linearLayoutInner.addView(tvPackagePrice)
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

    private fun deletePackage(packageID: String, userName: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("hotel/$userName/hotelPackages")
        databaseReference.child(packageID).removeValue().addOnSuccessListener {
            var userNameBundle = Bundle()
            userNameBundle.putString("userName", userName)

            val allMealFragment = HotelPackageDisplayFragment()
            allMealFragment.arguments = userNameBundle
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel, allMealFragment).commit()
            Toast.makeText(context, "Package Data was Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {

        }
    }
}