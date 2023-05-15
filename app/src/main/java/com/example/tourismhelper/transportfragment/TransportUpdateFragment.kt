package com.example.tourismhelper.transportfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tourismhelper.R
import com.example.tourismhelper.TransportMainActivity
import com.example.tourismhelper.TransportProviderMoreActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TransportUpdateFragment : Fragment() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transport_update, container, false)

        val regNum = arguments?.getString("regNum")

        var tvownerName = view.findViewById<EditText>(R.id.vehicleOwnerName_Transport)
        var tvownerNIC = view.findViewById<EditText>(R.id.vehicleOwnerNIC_Transport)
        var tvownerPhone = view.findViewById<EditText>(R.id.vehicleOwnerContact_Transport)
        var tvownerRegNum = view.findViewById<EditText>(R.id.vehicleRegID_Transport)

        databaseReference = FirebaseDatabase.getInstance().getReference("transport_provider")
        databaseReference.child(regNum.toString()).get().addOnSuccessListener {
            if(it.exists()){
                var ownerNameFromDB = it.child("vownerName").value.toString()
                var ownerNICFromDB = it.child("vownerNIC").value.toString()
                var ownerPhoneFromDB = it.child("vownerPhone").value.toString()
                var ownerRegNumFromDB = it.child("vregNum").value.toString()

                tvownerName.setText(ownerNameFromDB)
                tvownerNIC.setText(ownerNICFromDB)
                tvownerPhone.setText(ownerPhoneFromDB)
                tvownerRegNum.setText(ownerRegNumFromDB)

            }

            else{
                Toast.makeText(context, "User Does Not Exist", Toast.LENGTH_SHORT).show()
            }
        }

        val register2Button_Transport_Update = view.findViewById<Button>(R.id.register2Button_Transport_Update)
        register2Button_Transport_Update.setOnClickListener {
            updateteAccTransport(regNum.toString(),tvownerNIC.text.toString(),tvownerName.text.toString(),tvownerPhone.text.toString(),tvownerRegNum.text.toString())
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerTransport,TransportProfileFragment()).commit()
        }

        return  view


    }

    private fun updateteAccTransport(regNum: String, vownerNIC: String, vownerName: String, vownerPhone: String, toString4: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("transport_provider")
        val tregNum = mapOf<String,String>(
            "vownerName" to vownerName,
            "vownerNIC" to vownerNIC,
            "vownerPhone" to vownerPhone,

        )

        databaseReference.child(regNum.toString()).updateChildren(tregNum).addOnSuccessListener {
            Toast.makeText(context, "Successfully Updated Transport Provider", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{


        }
    }


}