package com.example.tourismhelper

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.*
import com.example.tourismhelper.database.ResHotelOwnerData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ResHotelRegisterActivity : AppCompatActivity() {

    private lateinit var selectedType: String
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_res_hotel_register)

        var register = supportActionBar
        register?.setTitle("Restaurant/Hotel Register")

        var fullName = findViewById<EditText>(R.id.edtRegFullName)
        var userName = findViewById<EditText>(R.id.edtRegUN)
        var email = findViewById<EditText>(R.id.edtRegEmail)
        var password = findViewById<EditText>(R.id.edtRegPassword)
        var retypePassword = findViewById<EditText>(R.id.edtRegRetypePassword)
        var phone = findViewById<EditText>(R.id.edtRegPhone)

        var bType = findViewById<Spinner>(R.id.selectResHotel)
        //Getting bType Value from Spinner
        bType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedType = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        var termCondition = findViewById<CheckBox>(R.id.checkRegAccept)

        val nextReg = findViewById<Button>(R.id.btnNextRegResHotel)

        nextReg.setOnClickListener {
            /*var intent = Intent(this, HotelRegisterActivity::class.java)
            startActivity(intent)*/
            var fullNameTxt = fullName.text.toString()
            var userNameTxt = userName.text.toString()
            var emailTxt = email.text.toString()
            var passwordTxt = password.text.toString()
            var reTypePasswordTxt = retypePassword.text.toString()
            var phoneTxt = phone.text.toString()

            /*databaseReference = FirebaseDatabase.getInstance().getReference("hotel_restaurant_Owner")
            val user = ResHotelOwnerData(fullNameTxt,userNameTxt,emailTxt,phoneTxt,selectedType,passwordTxt)
            databaseReference.child(userNameTxt).setValue(user).addOnSuccessListener {
                Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this, "Faild", Toast.LENGTH_SHORT).show()
            }*/

            if(!userNameTxt.contains(" ")){
                if(passwordTxt == reTypePasswordTxt && passwordTxt.isNotEmpty()){
                    if(termCondition.isChecked == true){
                        if(fullNameTxt.isNotEmpty() && userNameTxt.isNotEmpty() && emailTxt.isNotEmpty() && phoneTxt.isNotEmpty()){
                            if(phoneTxt.length == 10){
                                if( Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches()){
                                    if(selectedType == "Hotel"){
                                        val intent = Intent(this, HotelRegisterActivity::class.java)
                                        intent.putExtra("fullName", fullNameTxt)
                                        intent.putExtra("userName", userNameTxt)
                                        intent.putExtra("email", emailTxt)
                                        intent.putExtra("phone", phoneTxt)
                                        intent.putExtra("bType", selectedType)
                                        intent.putExtra("password", passwordTxt)
                                        startActivity(intent)
                                    }
                                    else if(selectedType == "Restaurant"){
                                        val intent = Intent(this, RestaurantRegisterActivity::class.java)
                                        intent.putExtra("fullName", fullNameTxt)
                                        intent.putExtra("userName", userNameTxt)
                                        intent.putExtra("email", emailTxt)
                                        intent.putExtra("phone", phoneTxt)
                                        intent.putExtra("bType", selectedType)
                                        intent.putExtra("password", passwordTxt)
                                        startActivity(intent)
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
                    else{
                        Toast.makeText(this,"Please Accept Term & Conditions", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this,"Retype Password Correctly", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"Username Cannot Contain Spaces", Toast.LENGTH_SHORT).show()
            }



            /*if(userNameTxt.isNotEmpty()){

            }
            else{
                Toast.makeText(this, "user name is empty", Toast.LENGTH_SHORT).show()
            }*/
        }
    }
}