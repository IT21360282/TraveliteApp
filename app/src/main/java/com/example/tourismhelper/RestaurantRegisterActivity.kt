package com.example.tourismhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.tourismhelper.database.HotelData
import com.example.tourismhelper.database.ResHotelOwnerData
import com.example.tourismhelper.database.RestaurantData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RestaurantRegisterActivity : AppCompatActivity() {

    private lateinit var databaseReferenceOwner: DatabaseReference
    private lateinit var databaseReferenceRestaurant: DatabaseReference
    private lateinit var starRateTxt: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_register)

        supportActionBar?.setTitle("Restaurant Register")

        var fullName = intent.getStringExtra("fullName").toString()
        var userName = intent.getStringExtra("userName").toString()
        var email = intent.getStringExtra("email").toString()
        var phone = intent.getStringExtra("phone").toString()
        var bType = intent.getStringExtra("bType").toString()
        var password = intent.getStringExtra("password").toString()

        var RestaurantName = findViewById<EditText>(R.id.edtResName)
        var RestaurantRegNum = findViewById<EditText>(R.id.edtResRegNo)
        var businessEmail = findViewById<EditText>(R.id.edtResBusinessEmail)
        var businessPhone = findViewById<EditText>(R.id.edtResBusinessPhone)
        var location = findViewById<EditText>(R.id.edtResBusinessLoc)

        var starRate = findViewById<Spinner>(R.id.selectResStarRate)
        starRate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                starRateTxt = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        var regHotelTitle = findViewById<TextView>(R.id.regHotelTitle)
        regHotelTitle.setText("Fill Details To Register '$userName'")

        var btnResStartMain = findViewById<Button>(R.id.btnSubmitRestaurant)
        btnResStartMain.setOnClickListener {

            var restaurantNameTxt = RestaurantName.text.toString()
            var restaurantRegNumTxt = RestaurantRegNum.text.toString()
            var businessEmailTxt = businessEmail.text.toString()
            var businessPhoneTxt = businessPhone.text.toString()
            var locationTxt = location.text.toString()

            databaseReferenceOwner = FirebaseDatabase.getInstance().getReference("hotel_restaurant_Owner")
            val user = ResHotelOwnerData(fullName,userName,email,phone,bType,password)
            databaseReferenceOwner.child(userName).setValue(user).addOnSuccessListener {
                //Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this, "Failed to Save Owner Data", Toast.LENGTH_SHORT).show()
            }

            databaseReferenceRestaurant = FirebaseDatabase.getInstance().getReference("restaurant")
            val restaurant = RestaurantData(restaurantNameTxt,restaurantRegNumTxt,businessEmailTxt,businessPhoneTxt,starRateTxt,locationTxt)
            databaseReferenceRestaurant.child(userName).setValue(restaurant).addOnSuccessListener {
                Toast.makeText(this, "You Registered Successfully!", Toast.LENGTH_SHORT).show()
                var intent = Intent(this,ResHotelLoginActivity::class.java)
                intent.putExtra("userName", userName)
                startActivity(intent)
            }.addOnFailureListener{
                Toast.makeText(this, "Failed to Save Restaurant Data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}