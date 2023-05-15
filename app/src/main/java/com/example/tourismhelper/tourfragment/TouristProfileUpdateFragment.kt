package com.example.tourismhelper.tourfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tourismhelper.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.w3c.dom.Text

class TouristProfileUpdateFragment : Fragment() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var firstNameTxt: String
    private lateinit var lastNameTxt: String
    private lateinit var emailTxt: String
    private lateinit var phoneTxt: String
    private lateinit var countryTxt: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tourist_profile_update, container, false)

        val logtour = arguments?.getString("logtour")

        var firstName = view.findViewById<EditText>(R.id.touristfirstname)
        var lastName = view.findViewById<EditText>(R.id.touristlastname)
        var email = view.findViewById<EditText>(R.id.touristemail)
        var phone = view.findViewById<EditText>(R.id.touristcontactnumber)
        var country = view.findViewById<EditText>(R.id.touristbirthcountry)
        firstName.setText(logtour.toString())

        databaseReference = FirebaseDatabase.getInstance().getReference("tourist")

        databaseReference.child(logtour.toString()).get().addOnSuccessListener {
            if(it.exists()){
                val fnFromDB = it.child("touristFirstname").value.toString()
                val lnFromDB = it.child("touristLastname").value.toString()
                val emailFromDB = it.child("touristEmail").value.toString()
                val phoneFromDB = it.child("touristContactNumber").value.toString()
                val countryFromDB = it.child("touristBirthCountry").value.toString()

                firstName.setText(fnFromDB)
                lastName.setText(lnFromDB)
                email.setText(emailFromDB)
                phone.setText(phoneFromDB)
                country.setText(countryFromDB)
            }

        }.addOnFailureListener {

        }

        firstNameTxt = firstName.text.toString()
        lastNameTxt = lastName.text.toString()
        emailTxt = email.text.toString()
        phoneTxt = phone.text.toString()
        countryTxt = country.text.toString()

        val btnUpdate = view.findViewById<Button>(R.id.btnUpdate)
        btnUpdate.setOnClickListener {
            updateProfile(logtour)
        }

        return view
    }

    private fun updateProfile(logtour: String?) {
        databaseReference = FirebaseDatabase.getInstance().getReference("tourist")

        val data = mapOf<String,String>(
            "touristFirstname" to firstNameTxt,
            "touristLastname" to lastNameTxt,
            "touristEmail" to emailTxt,
            "touristContactNumber" to phoneTxt,
            "touristBirthCountry" to countryTxt
        )

        databaseReference.child(logtour.toString()).updateChildren(data).addOnSuccessListener {
            Toast.makeText(context,"Profile is Updated Successfully",Toast.LENGTH_SHORT)
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerTourist,TouristProfileFragment()).commit()

        }.addOnFailureListener {
            Toast.makeText(context,"Error Occurred, Profile is not Updated",Toast.LENGTH_SHORT)
        }
    }
}