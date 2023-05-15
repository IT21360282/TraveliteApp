package com.example.tourismhelper

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.database.ktx.values

class ResHotelLoginActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurant_hotel_login)

        var login = supportActionBar
        login?.setTitle("Restaurant/Hotel Login")

        var UNorEmail = findViewById<EditText>(R.id.edtLoginUNorEmail)
        var password = findViewById<EditText>(R.id.edtLoginPass)



        var btnLogin = findViewById<Button>(R.id.btnSubmitLogin)
        btnLogin.setOnClickListener{
            validate(UNorEmail.text.toString(),password.text.toString())
        }


        var LoginToReg = findViewById<TextView>(R.id.txtViewLoginToReg)
        LoginToReg.setOnClickListener {
            Toast.makeText(this, "User Registration", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ResHotelRegisterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun validate(userTxt:String, passwordTxt:String) {
        val overlayView = View(this)
        overlayView.setBackgroundResource(R.drawable.popup_overlay)
        overlayView.alpha = 1f
        overlayView.isClickable = false
        val container = findViewById<FrameLayout>(R.id.popupContainer)

        val loadingPopupView = layoutInflater.inflate(R.layout.layout_loading_popup, null)
        val loadingPopupWindow = PopupWindow(loadingPopupView,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        loadingPopupWindow.isFocusable = true

        container.addView(overlayView)
        loadingPopupWindow.showAtLocation(window.decorView, Gravity.CENTER, 0, 0)

        databaseReference = FirebaseDatabase.getInstance().getReference("hotel_restaurant_Owner")
        databaseReference.child(userTxt).get().addOnSuccessListener {
            if(it.exists()){
                var userNameFromDB = it.child("userName").value.toString()
                var passwordFromDB = it.child("password").value.toString()
                var bTypeFromDB = it.child("btype").value.toString()
                if(passwordTxt == passwordFromDB){
                    if(bTypeFromDB == "Hotel"){
                        Toast.makeText(this, "Logged In Successfully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, ResHotelMainActivity::class.java)
                        intent.putExtra("userName", userNameFromDB)
                        startActivity(intent)
                        finish()
                    }
                    else if(bTypeFromDB == "Restaurant"){
                        Toast.makeText(this, "Logged In Successfully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, RestaurantMainActivity::class.java)
                        intent.putExtra("userName", userNameFromDB)
                        startActivity(intent)
                        finish()
                    }
                    else{

                    }
                }
                else{
                    Toast.makeText(this, "Username or Password is Incorrect", Toast.LENGTH_SHORT).show()
                    container.removeView(overlayView)
                    loadingPopupWindow.dismiss()
                }
            }
            else{
                Toast.makeText(this, "User Does not Exist", Toast.LENGTH_SHORT).show()
                container.removeView(overlayView)
                loadingPopupWindow.dismiss()
            }
        }.addOnFailureListener{
            Toast.makeText(this, "Failed to Get Data, Try Again", Toast.LENGTH_SHORT).show()
            container.removeView(overlayView)
            loadingPopupWindow.dismiss()
        }


       // loadingPopupWindow.dismiss()
        /*var un = "nilanka"
        var pass = "SN1234"

        var bType = "restaurant"
        if(un == userTxt && pass == passwordTxt){

            if(bType == "hotel"){
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ResHotelMainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, RestaurantMainActivity::class.java)
                startActivity(intent)
            }
        }
        else{
            Toast.makeText(this, "Incorrect UserName or Password", Toast.LENGTH_SHORT).show()
        }*/

    }
}