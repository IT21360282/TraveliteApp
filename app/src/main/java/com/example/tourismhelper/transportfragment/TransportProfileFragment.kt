package com.example.tourismhelper.transportfragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tourismhelper.R
import com.example.tourismhelper.TransportMainActivity
import com.example.tourismhelper.TransportProviderMoreActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TransportProfileFragment : Fragment() {


    private lateinit var databaseReference: DatabaseReference



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transport_profile, container, false)



        val regNum = arguments?.getString("regNum")

        var tvownerName = view.findViewById<TextView>(R.id.textView_profileOwnername_Transport)
        var tvownerNIC = view.findViewById<TextView>(R.id.textView_profileOwnerNIC_Transport)
        var tvownerPhone = view.findViewById<TextView>(R.id.textView_profileOwnerContact_Transport)
        var tvownerRegNum = view.findViewById<TextView>(R.id.textView_profileOwnerReg_Transport)
        var tvtype = view.findViewById<TextView>(R.id.textView_profileOwnerType_Transport)

        databaseReference = FirebaseDatabase.getInstance().getReference("transport_provider")
        databaseReference.child(regNum.toString()).get().addOnSuccessListener {
            if(it.exists()){
                var ownerNameFromDB = it.child("vownerName").value.toString()
                var ownerNICFromDB = it.child("vownerNIC").value.toString()
                var ownerPhoneFromDB = it.child("vownerPhone").value.toString()
                var ownerRegNumFromDB = it.child("vregNum").value.toString()
                var typeFromDB = it.child("vtype").value.toString()

                tvownerName.setText(ownerNameFromDB)
                tvownerNIC.setText(ownerNICFromDB)
                tvownerPhone.setText(ownerPhoneFromDB)
                tvownerRegNum.setText(ownerRegNumFromDB)
                tvtype.setText(typeFromDB)

            }

            else{
                Toast.makeText(context, "User Does Not Exist", Toast.LENGTH_SHORT).show()
            }
        }

        val button_providerDeactivate_Transport = view.findViewById<Button>(R.id.button_providerDeactivate_Transport)
        button_providerDeactivate_Transport.setOnClickListener {
            Toast.makeText(context , "Transport  Update", Toast.LENGTH_SHORT).show()
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerTransport,TransportProfileFragment()).commit()
        }

        val button_providerUpdate_Transport = view.findViewById<Button>(R.id.button_providerUpdate_Transport)
        button_providerUpdate_Transport.setOnClickListener {
            Toast.makeText(context , "Transport  Update", Toast.LENGTH_SHORT).show()
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerTransport,TransportUpdateFragment()).commit()
        }
        return  view
    }

}