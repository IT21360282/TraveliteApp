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
import com.example.tourismhelper.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TransportNotificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transport_notification, container, false)


        /*  val sName = arguments?.getString("sName")

        var tbookName = view.findViewById<TextView>(R.id.textView_notification_Transport_Name)
        var tbookDate = view.findViewById<TextView>(R.id.textView_notification_Transport_Date)
        var tbookAddress = view.findViewById<TextView>(R.id.textView_notification_Transport_Address)
        var tbookPlace = view.findViewById<TextView>(R.id.textView_notification_Transport_Place)

        databaseReference = FirebaseDatabase.getInstance().getReference("transport_book")
        databaseReference.child(sName.toString()).get().addOnSuccessListener {
            if(it.exists()){
                var tbookNameFromDB = it.child("touristTransportName").value.toString()
                var tbookDateFromDB = it.child("touristTransportAddress").value.toString()
                var tbookAddressFromDB = it.child("touristTransportDate").value.toString()
                var tbookPlaceFromDB = it.child("touristTransportPlace").value.toString()

                tbookName.setText(tbookNameFromDB)
                tbookDate.setText(tbookDateFromDB)
                tbookAddress.setText(tbookAddressFromDB)
                tbookPlace.setText(tbookPlaceFromDB)

            }

            else{
                Toast.makeText(context, "User Does Not Exist", Toast.LENGTH_SHORT).show()
            }
        }


        // Delete Button
        val button_cancelbooking_Transport = view.findViewById<Button>(R.id.button_cancelbooking_Transport)
        button_cancelbooking_Transport.setOnClickListener {
            cancelBookTourist("touristTransportName")
            val intent = Intent(context, TransportLoginActivity::class.java)
            startActivity(intent)

        }
*/
        return  view
    }

/*
    // Delete Function for Delete Transport Provider Profile
    private fun cancelBookTourist(sName:String){
        databaseReference = FirebaseDatabase.getInstance().getReference("transport_book")
        databaseReference.child(regNum.toString()).removeValue().addOnSuccessListener {
            Toast.makeText(context, "Your Booking is Canceled", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{



        }}



    }

*/
}
