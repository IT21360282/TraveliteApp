package com.example.tourismhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.tourismhelper.transportDatabase.TransportProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class TransportRegisterActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var vtypeTxt: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transport_register)

        var registerTravel = supportActionBar
        registerTravel?.setTitle("Transport Register")

        var vownerName = findViewById<EditText>(R.id.vehicleOwnerName_Transport)
        var vownerNIC = findViewById<EditText>(R.id.vehicleOwnerNIC_Transport)
        var vownerPhone = findViewById<EditText>(R.id.vehicleOwnerContact_Transport)
        var vregNum = findViewById<EditText>(R.id.vehicleRegID_Transport)
        var password = findViewById<EditText>(R.id.vehicleOwnerPassword_Transport)
        var retypePassword = findViewById<EditText>(R.id.vehicleOwnerRetypePassword_Transport)

        var vtype = findViewById<Spinner>(R.id.vehicleType_Transport)
        vtype.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                vtypeTxt = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        var transportRegSumbitBtn = findViewById<Button>(R.id.register2Button_Transport)
        transportRegSumbitBtn.setOnClickListener {

            if(vregNum.text.toString().isNotEmpty()) {
                if(vownerPhone.text.toString().matches(Regex("^[+]?[0-9]{10,13}\$"))) {
                    databaseReference =
                        FirebaseDatabase.getInstance().getReference("transport_provider")
                    var transportProvider = TransportProvider(
                        vownerName.text.toString(),
                        vownerNIC.text.toString(),
                        vownerPhone.text.toString(),
                        vregNum.text.toString(),
                        vtypeTxt,
                        password.text.toString()
                    )

                    databaseReference.child(vregNum.text.toString()).setValue(transportProvider)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Successfully Register", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener {
                            Toast.makeText(this, "Failed to Register, Try Again", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(this, "Please Enter a Valid Phone Number", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please Enter Valid Register Number", Toast.LENGTH_SHORT).show()
            }
        }

    }
}

