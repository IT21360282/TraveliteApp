package com.example.tourismhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*
import com.example.tourismhelper.touristDatabase.tourist
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class TouristRegisterActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var touristTxt: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tourist_register)

        var registerTravel = supportActionBar
        registerTravel?.setTitle("Tourist Register")

        var touristFirstname = findViewById<EditText>(R.id.touristfirstname)
        var touristLastname = findViewById<EditText>(R.id.touristlastname)
        var touristemail = findViewById<EditText>(R.id.touristemail)
        var touristUsername = findViewById<EditText>(R.id.touristusername)
        var touristPassword = findViewById<EditText>(R.id.touristpassword)
        var touristContactNumber = findViewById<EditText>(R.id.touristcontactnumber)
        var touristBirthCountry = findViewById<EditText>(R.id.touristbirthcountry)


        var touristGender = findViewById<Spinner>(R.id.touristgender)
        touristGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                touristTxt = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        var touristRegSumbitBtn = findViewById<Button>(R.id.touristbtnReg1)
        touristRegSumbitBtn.setOnClickListener {

            if (touristUsername.text.toString().isNotEmpty()) {
                if (touristContactNumber.text.toString().matches(Regex("^[0-9]{13}\$"))) {
                    if(touristPassword.text.toString().length>=4){
                        if( Patterns.EMAIL_ADDRESS.matcher(touristemail.text.toString()).matches()){
                            databaseReference = FirebaseDatabase.getInstance().getReference("tourist")
                            var Tourists = tourist(
                                touristFirstname.text.toString(),
                                touristLastname.text.toString(),
                                touristemail.text.toString(),
                                touristUsername.text.toString(),
                                touristPassword.text.toString(),
                                touristContactNumber.text.toString(),
                                touristBirthCountry.text.toString(),
                                touristTxt,

                                )

                            databaseReference.child(touristUsername.text.toString()).setValue(Tourists)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "Successfully Register", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this,TouristLoginActivity::class.java))
                                    finish()
                                }.addOnFailureListener {
                                    Toast.makeText(
                                        this,
                                        "Failed to Register, Try Again",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                        }
                        else{
                            Toast.makeText(this, "This is an invalid email", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Toast.makeText(this, "Password should contain atleast 4 characters", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    Toast.makeText(this, "This is an invalid number", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Please Enter Valid Register Number", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}




