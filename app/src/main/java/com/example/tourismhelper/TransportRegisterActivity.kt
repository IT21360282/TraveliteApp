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
    private lateinit var vTypeTxt: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transport_register)

        var registerTravel = supportActionBar
        registerTravel?.setTitle("Transport Register")

        var vOwnerName = findViewById<EditText>(R.id.vehicleOwnerName_Transport)
        var vOwnerNIC = findViewById<EditText>(R.id.vehicleOwnerNIC_Transport)
        var vOwnerPhone = findViewById<EditText>(R.id.vehicleOwnerContact_Transport)
        var vRegNum = findViewById<EditText>(R.id.vehicleRegID_Transport)
        var password = findViewById<EditText>(R.id.vehicleOwnerPassword_Transport)
        var retypePassword = findViewById<EditText>(R.id.vehicleOwnerRetypePassword_Transport)

        var vType = findViewById<Spinner>(R.id.vehicleType_Transport)
        vType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                vTypeTxt = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        var transportRegSumbitBtn = findViewById<Button>(R.id.register2Button_Transport)
        transportRegSumbitBtn.setOnClickListener {

            if(vRegNum.text.toString().isNotEmpty()) {
                databaseReference =
                    FirebaseDatabase.getInstance().getReference("transport_provider")
                var transportProvider = TransportProvider(
                    vOwnerName.text.toString(),
                    vOwnerNIC.text.toString(),
                    vOwnerPhone.text.toString(),
                    vRegNum.text.toString(),
                    vTypeTxt,
                    password.text.toString()
                )

                databaseReference.child(vRegNum.text.toString()).setValue(transportProvider)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Successfully Register", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                    Toast.makeText(this, "Failed to Register, Try Again", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "Please Enter Valid Register Number", Toast.LENGTH_SHORT).show()
            }
        }

    }
}