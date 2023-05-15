package com.example.tourismhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*
import com.example.tourismhelper.database.HotelData
import com.example.tourismhelper.database.ResHotelOwnerData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HotelRegisterActivity : AppCompatActivity() {

    private lateinit var databaseReferenceOwner: DatabaseReference
    private lateinit var databaseReferenceHotel: DatabaseReference
    private lateinit var starRateTxt: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_register)

        supportActionBar?.setTitle("Hotel Register")

        var fullName = intent.getStringExtra("fullName").toString()
        var userName = intent.getStringExtra("userName").toString()
        var email = intent.getStringExtra("email").toString()
        var phone = intent.getStringExtra("phone").toString()
        var bType = intent.getStringExtra("bType").toString()
        var password = intent.getStringExtra("password").toString()

        var hotelName = findViewById<EditText>(R.id.edtHotelName)
        var hotelRegNum = findViewById<EditText>(R.id.edtHotelRegNo)
        var businessEmail = findViewById<EditText>(R.id.edtBusinessEmail)
        var businessPhone = findViewById<EditText>(R.id.edtBusinessPhone)
        var location = findViewById<EditText>(R.id.edtBusinessLoc)
        var countRooms = findViewById<EditText>(R.id.edtCountRooms)

        var starRate = findViewById<Spinner>(R.id.selectHotelStarRate)
        starRate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                starRateTxt = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        var regHotelTitle = findViewById<TextView>(R.id.regHotelTitle)
        regHotelTitle.setText("Fill Details To Register '$userName'")

        var btnResStartMain = findViewById<Button>(R.id.btnSubmitHotel)
        btnResStartMain.setOnClickListener {
            /*var intent = Intent(this,RestaurantMainActivity::class.java)
            startActivity(intent)*/

            var hotelNameTxt = hotelName.text.toString()
            var hotelRegNumTxt = hotelRegNum.text.toString()
            var businessEmailTxt = businessEmail.text.toString()
            var businessPhoneTxt = businessPhone.text.toString()
            var locationTxt = location.text.toString()
            var countRoomsTxt = countRooms.text.toString()

            if(hotelNameTxt.isNotEmpty() && hotelRegNumTxt.isNotEmpty() && businessEmailTxt.isNotEmpty() && businessPhoneTxt.isNotEmpty() &&
                    locationTxt.isNotEmpty() && countRoomsTxt.isNotEmpty()){
                if(businessPhoneTxt.length  == 10){
                    if(Patterns.EMAIL_ADDRESS.matcher(businessEmailTxt).matches()){

                        databaseReferenceOwner = FirebaseDatabase.getInstance().getReference("hotel_restaurant_Owner")
                        val user = ResHotelOwnerData(fullName,userName,email,phone,bType,password)
                        databaseReferenceOwner.child(userName).setValue(user).addOnSuccessListener {
                            //Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener{
                            Toast.makeText(this, "Failed to Save Owner Data", Toast.LENGTH_SHORT).show()
                        }

                        databaseReferenceHotel = FirebaseDatabase.getInstance().getReference("hotel")
                        val hotel = HotelData(hotelNameTxt,hotelRegNumTxt,businessEmailTxt,businessPhoneTxt,starRateTxt,locationTxt,countRoomsTxt)
                        databaseReferenceHotel.child(userName).setValue(hotel).addOnSuccessListener {
                            Toast.makeText(this, "You Registered Successfully!", Toast.LENGTH_SHORT).show()
                            var intent = Intent(this,ResHotelLoginActivity::class.java)
                            intent.putExtra("userName", userName)
                            startActivity(intent)
                        }.addOnFailureListener{
                            Toast.makeText(this, "Failed to Save Hotel Data", Toast.LENGTH_SHORT).show()
                        }

                    }
                    else{
                        Toast.makeText(this,"Invalid Email Format", Toast.LENGTH_SHORT).show()
                    }

                }
                else{
                    Toast.makeText(this,"Invalid Contact Number Format", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"Please Fill All Fields", Toast.LENGTH_SHORT).show()
            }


        }
    }
}