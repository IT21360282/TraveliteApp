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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TransportHistoryFragment : Fragment() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transport_history, container, false)

        val bookNum = arguments?.getString("bookNum")

        var tbookName = view.findViewById<TextView>(R.id.textView_historyLogo_Transport)
        var tbookContact = view.findViewById<TextView>(R.id.textView_historyName_Transport)
        var tbookPlace = view.findViewById<TextView>(R.id.textView_historyKM_Transport)
        var tbookDate = view.findViewById<TextView>(R.id.textView_historyLKR_Transport)

        databaseReference = FirebaseDatabase.getInstance().getReference("transport_book")
        databaseReference.child(bookNum.toString()).get().addOnSuccessListener {
            if(it.exists()){
                var tbookNameFromDB = it.child("touristTransportName").value.toString()
                var tbookContactFromDB = it.child("touristTransportContact").value.toString()
                var tbookPlaceFromDB = it.child("touristTransportPlace").value.toString()
                var tbookDateFromDB = it.child("touristTransportDate").value.toString()

                tbookName.setText(tbookNameFromDB)
                tbookContact.setText(tbookContactFromDB)
                tbookPlace.setText(tbookPlaceFromDB)
                tbookDate.setText(tbookDateFromDB)

            }
            else{
                Toast.makeText(context, "User Does Not Exist", Toast.LENGTH_SHORT).show()
            }
        }

        return  view
    }
}