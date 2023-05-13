package com.example.tourismhelper.tourfragment

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
import com.example.tourismhelper.TouristLoginActivity
import com.example.tourismhelper.TouristMainActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TouristProfileFragment : Fragment() {


    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tourist_profile, container, false)

        val btnUpdate = view.findViewById<Button>(R.id.btnUpdateProf)
        btnUpdate.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerTourist,TouristProfileUpdateFragment()).commit()
        }

        val logtour = arguments?.getString("logtour")

        var tourfirstname = view.findViewById<TextView>(R.id.textView11)
        var tourlastname = view.findViewById<TextView>(R.id.textView12)
        var touremail = view.findViewById<TextView>(R.id.textView13)
        var tourcontact = view.findViewById<TextView>(R.id.textView14)
        var tourcountry = view.findViewById<TextView>(R.id.textView15)

        databaseReference = FirebaseDatabase.getInstance().getReference("tourist")
        databaseReference.child(logtour.toString()).get().addOnSuccessListener {
            if(it.exists()){
                var touristfirstFromDB = it.child("touristFirstname").value.toString()
                var touristlastFromDB = it.child("touristLastname").value.toString()
                var touristmailFromDB = it.child("touristEmail").value.toString()
                var touristcontactFromDB = it.child("touristContactNumber").value.toString()
                var touristcountryFromDB = it.child("touristBirthCountry").value.toString()

                tourfirstname.setText(touristfirstFromDB)
                tourlastname.setText(touristlastFromDB)
                touremail.setText(touristmailFromDB)
                tourcontact.setText(touristcontactFromDB)
                tourcountry.setText(touristcountryFromDB)

            }

            else{
                Toast.makeText(context, "User Does Not Exist", Toast.LENGTH_SHORT).show()
            }
        }
         val btnDeleteProf = view.findViewById<Button>(R.id.btnDeleteProf)
        btnDeleteProf.setOnClickListener {
            deactivateprofile("touristUsername.")
            val intent = Intent(context,TouristLoginActivity:: class.java)
            startActivity(intent)
        }

         val btnUpdateProf = view.findViewById<Button>(R.id.btnUpdateProf)
        btnUpdateProf.setOnClickListener {
            Toast.makeText(context , "Update the Tourist", Toast.LENGTH_SHORT).show()
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerTourist,TouristProfileUpdateFragment()).commit()
        }




        return  view

    }
    private fun deactivateprofile(logtour : String ){
        databaseReference = FirebaseDatabase.getInstance().getReference("tourist")
        databaseReference.child(logtour.toString()).removeValue().addOnSuccessListener {
            Toast.makeText(context, "Prolife Deleted Successfully", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener{
            }
    }
}