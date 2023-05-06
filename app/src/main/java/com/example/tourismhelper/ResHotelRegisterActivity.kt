package com.example.tourismhelper

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

class ResHotelRegisterActivity : AppCompatActivity() {

    private lateinit var selectedType: String
    private lateinit var userNameTxt: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_res_hotel_register)

        var register = supportActionBar
        register?.setTitle("Restaurant/Hotel Register")

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

                Toast.makeText(applicationContext, "Bussiness Type is Selected as '$selectedType'", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        var termCondition = findViewById<CheckBox>(R.id.checkRegAccept)
        val nextReg = findViewById<Button>(R.id.btnNextRegResHotel)

        nextReg.setOnClickListener {
            /*var intent = Intent(this, HotelRegisterActivity::class.java)
            startActivity(intent)*/
            if(userName.text.toString().isEmpty()){
                Toast.makeText(this, "user name is  empty", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "user name is not empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}