package com.example.tourismhelper.reshotelfragment

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Patterns
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.example.tourismhelper.MainActivity
import com.example.tourismhelper.R
import com.example.tourismhelper.ResHotelMainActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.regex.Pattern

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResHotelProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResHotelProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var databaseReferenceOwner: DatabaseReference
    private lateinit var databaseReferenceHotel: DatabaseReference
    private lateinit var databaseReference: DatabaseReference
    private lateinit var password: String
    private lateinit var fullNameFromDB: String
    private lateinit var emailFromDB: String
    private lateinit var phoneFromDB: String
    private lateinit var hotelNameFromDB: String
    private lateinit var bEmailFromDB: String
    private lateinit var bPhoneFromDB: String
    private lateinit var bLocationFromDB: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_res_hotel_profile, container, false)

        var username = arguments?.getString("userName")

        //popup overlay
        val overlayView = View(requireContext())
        overlayView.setBackgroundResource(R.drawable.popup_overlay)
        overlayView.alpha = 1f
        overlayView.isClickable = false
        val container = view.findViewById<FrameLayout>(R.id.popupContainer)

        var profHotelName = view.findViewById<TextView>(R.id.tvBusinessNameValue)
        var profOwnerName = view.findViewById<TextView>(R.id.tvNameValue)

        //owner details
        var profOwnerNameBody = view.findViewById<TextView>(R.id.tvOwnerName)
        var profOwnerEmail = view.findViewById<TextView>(R.id.tvOwnerEmail)
        var profOwnerPhone = view.findViewById<TextView>(R.id.tvOwnerPhone)

        //business details
        var profHotelNameBody = view.findViewById<TextView>(R.id.tvHotelName)
        var profBusinessType = view.findViewById<TextView>(R.id.tvBusinessType)
        var profBusinessEmail = view.findViewById<TextView>(R.id.tvBusinessEmailVal)
        var profBusinessPhone = view.findViewById<TextView>(R.id.tvBusinessPhoneValue)
        var profBusinessLocation = view.findViewById<TextView>(R.id.tvBusinessLocation)

        databaseReferenceOwner = FirebaseDatabase.getInstance().getReference("hotel_restaurant_Owner")
        databaseReferenceOwner.child(username.toString()).get().addOnSuccessListener {
            if(it.exists()){
                fullNameFromDB = it.child("fullName").value.toString()
                var userNameFromDB = it.child("userName").value.toString()
                emailFromDB = it.child("email").value.toString()
                phoneFromDB = it.child("phone").value.toString()
                var bTypeFromDB = it.child("btype").value.toString()
                password = it.child("password").value.toString()

                profOwnerName.setText("$fullNameFromDB")
                profOwnerNameBody.setText("$fullNameFromDB")
                profOwnerEmail.setText("$emailFromDB")
                profOwnerPhone.setText("$phoneFromDB")
                profBusinessType.setText("$bTypeFromDB")
            }
            else{
                //Toast.makeText(this, "User Does not Exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{

        }

        databaseReferenceHotel = FirebaseDatabase.getInstance().getReference("hotel")
        databaseReferenceHotel.child(username.toString()).get().addOnSuccessListener {
            if(it.exists()){
                hotelNameFromDB = it.child("hotelName").value.toString()
                var hotelRegNumFromDB = it.child("hotelRegNum").value.toString()
                bEmailFromDB = it.child("businessEmail").value.toString()
                bPhoneFromDB = it.child("businessPhone").value.toString()
                bLocationFromDB = it.child("businessLocation").value.toString()

                profHotelName.setText("$hotelNameFromDB")
                profHotelNameBody.setText("$hotelNameFromDB")
                profBusinessEmail.setText("$bEmailFromDB")
                profBusinessPhone.setText("$bPhoneFromDB")
                profBusinessLocation.setText("$bLocationFromDB")
            }
            else{
                //Toast.makeText(this, "User Does not Exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{

        }

        val btnDeactivate = view.findViewById<Button>(R.id.btnDeactivate)
        btnDeactivate.setOnClickListener {
            val popupView = layoutInflater.inflate(R.layout.layout_popup, null)

            val popupLinearLayout = popupView.findViewById<LinearLayout>(R.id.popupLinearLayout)

            val tvPopup = popupView.findViewById<TextView>(R.id.tvPopup)
            tvPopup.setText("Deactivate Account")

            val btnClose = popupView.findViewById<ImageButton>(R.id.closeBtn)

            val popupWindow = PopupWindow(popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            popupWindow.isFocusable = true

            popupWindow.showAtLocation(container, Gravity.CENTER, 0, 0)

            container.addView(overlayView)

            btnClose.setOnClickListener{
                container.removeView(overlayView)
                popupWindow.dismiss()
            }

            val tvEnterPass = TextView(context)
            tvEnterPass.text = "Enter Password to Deactivate:"
            tvEnterPass.textSize = 20f
            tvEnterPass.setTypeface(null, Typeface.BOLD)

            val edtPassword = EditText(context)
            edtPassword.hint = " Password"
            edtPassword.setBackgroundResource(R.drawable.styles_edit_text)
            edtPassword.isFocusable = true
            edtPassword.isClickable = true
            edtPassword.requestFocus()

            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(edtPassword, InputMethodManager.SHOW_IMPLICIT)

            val btnPopupLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            btnPopupLayoutParams.setMargins(10,10,10,15)

            val btnDeactivate = Button(context)
            btnDeactivate.text = "Deactivate"
            btnDeactivate.setBackgroundResource(R.drawable.btn_background)
            btnDeactivate.setTextColor(resources.getColor(R.color.white,null))
            btnDeactivate.layoutParams = btnPopupLayoutParams

            btnDeactivate.setOnClickListener {
                if(edtPassword.text.toString() == password.toString()){
                    deacticateAcc(username.toString())
                }
                else{
                    Toast.makeText(context, "Your Password is Incorrect, Try Again", Toast.LENGTH_SHORT).show()
                    edtPassword.text.clear()
                }
            }

            popupLinearLayout.addView(tvEnterPass)
            popupLinearLayout.addView(edtPassword)
            popupLinearLayout.addView(btnDeactivate)
        }

        val btnChangePassword = view.findViewById<Button>(R.id.changePassword)
        btnChangePassword.setOnClickListener {
            val popupView = layoutInflater.inflate(R.layout.layout_popup, null)

            val popupLinearLayout = popupView.findViewById<LinearLayout>(R.id.popupLinearLayout)

            val tvPopup = popupView.findViewById<TextView>(R.id.tvPopup)
            tvPopup.setText("Change Password")

            val btnClose = popupView.findViewById<ImageButton>(R.id.closeBtn)

            val popupWindow = PopupWindow(popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            popupWindow.isFocusable = true

            popupWindow.showAtLocation(container, Gravity.CENTER, 0, 0)

            container.addView(overlayView)

            btnClose.setOnClickListener{
                container.removeView(overlayView)
                popupWindow.dismiss()
            }

            val tvEnterPass = TextView(context)
            tvEnterPass.text = "Current Password:"
            tvEnterPass.textSize = 20f
            tvEnterPass.setTypeface(null, Typeface.BOLD)

            val edtPassword = EditText(context)
            edtPassword.hint = "  Current Password"
            edtPassword.setBackgroundResource(R.drawable.styles_edit_text)
            edtPassword.isFocusable = true
            edtPassword.isClickable = true
            edtPassword.requestFocus()

            val tvNewPass = TextView(context)
            tvNewPass.text = "New Password:"
            tvNewPass.textSize = 20f
            tvNewPass.setTypeface(null, Typeface.BOLD)

            val edtNewPassword = EditText(context)
            edtNewPassword.hint = "  New Password"
            edtNewPassword.setBackgroundResource(R.drawable.styles_edit_text)
            edtNewPassword.isFocusable = true
            edtNewPassword.isClickable = true
            edtNewPassword.requestFocus()

            val tvRetypeNewPass = TextView(context)
            tvRetypeNewPass.text = "Retype New Password:"
            tvRetypeNewPass.textSize = 20f
            tvRetypeNewPass.setTypeface(null, Typeface.BOLD)

            val edtRetypeNewPassword = EditText(context)
            edtRetypeNewPassword.hint = "  Retype New Password"
            edtRetypeNewPassword.setBackgroundResource(R.drawable.styles_edit_text)
            edtRetypeNewPassword.isFocusable = true
            edtRetypeNewPassword.isClickable = true
            edtRetypeNewPassword.requestFocus()

            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(edtPassword, InputMethodManager.SHOW_IMPLICIT)

            val btnPopupLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            btnPopupLayoutParams.setMargins(10,10,10,15)

            val btnUpdate = Button(context)
            btnUpdate.text = "Update"
            btnUpdate.setBackgroundResource(R.drawable.btn_background)
            btnUpdate.setTextColor(resources.getColor(R.color.white,null))
            btnUpdate.layoutParams = btnPopupLayoutParams

            btnUpdate.setOnClickListener {
                if(edtPassword.text.toString() == password.toString()){
                    if(edtNewPassword.text.toString() == edtRetypeNewPassword.text.toString()){
                        if(edtNewPassword.text.toString() == password.toString()){
                            Toast.makeText(context, "New Password Cannot be Current Password", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            changePassword(username.toString(), edtNewPassword.text.toString())
                            container.removeView(overlayView)
                            popupWindow.dismiss()
                        }

                    }
                    else{
                        Toast.makeText(context, "Retype New Password Correctly", Toast.LENGTH_SHORT).show()
                    }

                }
                else{
                    Toast.makeText(context, "Your Old Password is Incorrect, Try Again", Toast.LENGTH_SHORT).show()
                    edtPassword.text.clear()
                }
            }

            popupLinearLayout.addView(tvEnterPass)
            popupLinearLayout.addView(edtPassword)
            popupLinearLayout.addView(tvNewPass)
            popupLinearLayout.addView(edtNewPassword)
            popupLinearLayout.addView(tvRetypeNewPass)
            popupLinearLayout.addView(edtRetypeNewPassword)
            popupLinearLayout.addView(btnUpdate)
        }


        val btnUpdateProfile = view.findViewById<Button>(R.id.updateProfile)
        btnUpdateProfile.setOnClickListener {
            val popupView = layoutInflater.inflate(R.layout.layout_popup, null)

            val popupLinearLayout = popupView.findViewById<LinearLayout>(R.id.popupLinearLayout)

            val tvPopup = popupView.findViewById<TextView>(R.id.tvPopup)
            tvPopup.setText("Update Profile")

            val btnClose = popupView.findViewById<ImageButton>(R.id.closeBtn)

            val popupWindow = PopupWindow(popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            popupWindow.isFocusable = true

            popupWindow.showAtLocation(container, Gravity.CENTER, 0, 0)

            container.addView(overlayView)

            btnClose.setOnClickListener{
                container.removeView(overlayView)
                popupWindow.dismiss()
            }

            val tvName = TextView(context)
            tvName.text = "Name:"
            tvName.textSize = 20f
            tvName.setTypeface(null, Typeface.BOLD)

            val edtName = EditText(context)
            edtName.hint = "  Name"
            edtName.setText(fullNameFromDB)
            edtName.setBackgroundResource(R.drawable.styles_edit_text)
            edtName.setPadding(8,10,0,10)
            edtName.isFocusable = true
            edtName.isClickable = true
            edtName.requestFocus()

            val tvEmail = TextView(context)
            tvEmail.text = "Email:"
            tvEmail.textSize = 20f
            tvEmail.setTypeface(null, Typeface.BOLD)

            val edtEmail = EditText(context)
            edtEmail.hint = "  Email"
            edtEmail.setText(emailFromDB)
            edtEmail.setPadding(8,10,0,10)
            edtEmail.setBackgroundResource(R.drawable.styles_edit_text)
            edtEmail.isFocusable = true
            edtEmail.isClickable = true
            edtEmail.requestFocus()

            val tvPhone = TextView(context)
            tvPhone.text = "Contact Number:"
            tvPhone.textSize = 20f
            tvPhone.setTypeface(null, Typeface.BOLD)

            val edtPhone = EditText(context)
            edtPhone.hint = "  Contact Number"
            edtPhone.setText(phoneFromDB)
            edtPhone.setPadding(8,10,0,10)
            edtPhone.setBackgroundResource(R.drawable.styles_edit_text)
            edtPhone.isFocusable = true
            edtPhone.isClickable = true
            edtPhone.requestFocus()

            val tvBusinessName = TextView(context)
            tvBusinessName.text = "Business Name:"
            tvBusinessName.textSize = 20f
            tvBusinessName.setTypeface(null, Typeface.BOLD)

            val edtBusinessName = EditText(context)
            edtBusinessName.hint = "  Business Name"
            edtBusinessName.setText(hotelNameFromDB)
            edtBusinessName.setPadding(8,10,0,10)
            edtBusinessName.setBackgroundResource(R.drawable.styles_edit_text)
            edtBusinessName.isFocusable = true
            edtBusinessName.isClickable = true
            edtBusinessName.requestFocus()

            val tvBusinessEmail = TextView(context)
            tvBusinessEmail.text = "Business Email:"
            tvBusinessEmail.textSize = 20f
            tvBusinessEmail.setTypeface(null, Typeface.BOLD)

            val edtBusinessEmail = EditText(context)
            edtBusinessEmail.hint = "  Business Email"
            edtBusinessEmail.setText(bEmailFromDB)
            edtBusinessEmail.setPadding(8,10,0,10)
            edtBusinessEmail.setBackgroundResource(R.drawable.styles_edit_text)
            edtBusinessEmail.isFocusable = true
            edtBusinessEmail.isClickable = true
            edtBusinessEmail.requestFocus()

            val tvBusinessContact = TextView(context)
            tvBusinessContact.text = "Business Contact Number:"
            tvBusinessContact.textSize = 20f
            tvBusinessContact.setTypeface(null, Typeface.BOLD)

            val edtBusinessContact = EditText(context)
            edtBusinessContact.hint = "  Business Contact Number"
            edtBusinessContact.setText(bPhoneFromDB)
            edtBusinessContact.setPadding(8,10,0,10)
            edtBusinessContact.setBackgroundResource(R.drawable.styles_edit_text)
            edtBusinessContact.isFocusable = true
            edtBusinessContact.isClickable = true
            edtBusinessContact.requestFocus()

            val tvLocation = TextView(context)
            tvLocation.text = "Location:"
            tvLocation.textSize = 20f
            tvLocation.setTypeface(null, Typeface.BOLD)

            val edtLocation = EditText(context)
            edtLocation.hint = "  Location"
            edtLocation.setText(bLocationFromDB)
            edtLocation.setPadding(8,10,0,10)
            edtLocation.setBackgroundResource(R.drawable.styles_edit_text)
            edtLocation.isFocusable = true
            edtLocation.isClickable = true
            edtLocation.requestFocus()



            val btnPopupLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            btnPopupLayoutParams.setMargins(10,10,10,15)

            val btnUpdate = Button(context)
            btnUpdate.text = "Update"
            btnUpdate.setBackgroundResource(R.drawable.btn_background)
            btnUpdate.setTextColor(resources.getColor(R.color.white,null))
            btnUpdate.layoutParams = btnPopupLayoutParams

            btnUpdate.setOnClickListener {
                if(edtName.text.toString().isNotEmpty() && edtEmail.text.toString().isNotEmpty() && edtPhone.text.toString().isNotEmpty() &&
                        edtBusinessName.text.toString().isNotEmpty() && edtBusinessEmail.text.toString().isNotEmpty() &&
                        edtBusinessContact.text.toString().isNotEmpty() && edtLocation.text.toString().isNotEmpty()){
                    if(edtPhone.text.toString().length != 10 || edtBusinessContact.text.toString().length != 10){
                        Toast.makeText(context, "Invalid Mobile Number Format",Toast.LENGTH_SHORT).show()
                    }
                    if( !Patterns.EMAIL_ADDRESS.matcher(edtEmail.text.toString()).matches() || !Patterns.EMAIL_ADDRESS.matcher(edtBusinessEmail.text.toString()).matches()){
                        Toast.makeText(context, "Invalid Email Format",Toast.LENGTH_SHORT).show()
                    }
                    if(edtPhone.text.toString().length == 10 && edtBusinessContact.text.toString().length == 10 &&
                        Patterns.EMAIL_ADDRESS.matcher(edtEmail.text.toString()).matches() && Patterns.EMAIL_ADDRESS.matcher(edtBusinessEmail.text.toString()).matches()){
                        updateProfile(username.toString(),edtName.text.toString(),edtEmail.text.toString(),edtPhone.text.toString(),edtBusinessName.text.toString(),
                        edtBusinessEmail.text.toString(),edtBusinessContact.text.toString(),edtLocation.text.toString())
                        container.removeView(overlayView)
                        popupWindow.dismiss()
                    }
                }
                else{
                    Toast.makeText(context, "All Feild Should be Filled",Toast.LENGTH_SHORT).show()
                }

            }

            popupLinearLayout.addView(tvName)
            popupLinearLayout.addView(edtName)
            popupLinearLayout.addView(tvEmail)
            popupLinearLayout.addView(edtEmail)
            popupLinearLayout.addView(tvPhone)
            popupLinearLayout.addView(edtPhone)
            popupLinearLayout.addView(tvBusinessName)
            popupLinearLayout.addView(edtBusinessName)
            popupLinearLayout.addView(tvBusinessEmail)
            popupLinearLayout.addView(edtBusinessEmail)
            popupLinearLayout.addView(tvBusinessContact)
            popupLinearLayout.addView(edtBusinessContact)
            popupLinearLayout.addView(tvLocation)
            popupLinearLayout.addView(edtLocation)

            popupLinearLayout.addView(btnUpdate)
        }

        return view
    }

    private fun updateProfile(username :String, name: String, email: String, phone: String, bName: String, bEmail: String, bPhone: String, bLocation: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("hotel_restaurant_Owner")
        val owner = mapOf<String,String>(
            "fullName" to name,
            "email" to email,
            "phone" to phone
        )
        databaseReference.child(username.toString()).updateChildren(owner).addOnSuccessListener {
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {

        }
        databaseReference = FirebaseDatabase.getInstance().getReference("hotel")
        val hotel = mapOf<String,String>(
            "hotelName" to bName,
            "businessEmail" to bEmail,
            "businessPhone" to bPhone,
            "businessLocation" to bLocation
        )
        databaseReference.child(username.toString()).updateChildren(hotel).addOnSuccessListener {

        }.addOnFailureListener {

        }
    }

    private fun deacticateAcc(username:String){
        databaseReference = FirebaseDatabase.getInstance().getReference("hotel_restaurant_Owner")
        databaseReference.child(username.toString()).removeValue().addOnSuccessListener {
            Toast.makeText(context, "Your Account is Deactivated", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {

        }
        databaseReference = FirebaseDatabase.getInstance().getReference("hotel")
        databaseReference.child(username.toString()).removeValue().addOnSuccessListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }.addOnFailureListener {

        }
    }

    private fun changePassword(username: String, newPasseword: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("hotel_restaurant_Owner")
        val password = mapOf<String,String>(
            "password" to newPasseword
        )
        databaseReference.child(username.toString()).updateChildren(password).addOnSuccessListener {
            Toast.makeText(context, "Pasword is Successfully Updated", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(context, "Error Occurred, Password no Updated", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ResHotelProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ResHotelProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}