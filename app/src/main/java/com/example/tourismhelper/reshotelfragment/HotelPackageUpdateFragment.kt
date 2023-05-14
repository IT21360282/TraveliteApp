package com.example.tourismhelper.reshotelfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.tourismhelper.R
import com.example.tourismhelper.database.HotelPackageData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HotelPackageUpdateFragment : Fragment() {

    private lateinit var databaseReferencePackage: DatabaseReference
    private lateinit var packageServedForTxt: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hotel_package_update, container, false)

        var username = arguments?.getString("userName")
        var packageID = arguments?.getString("packageID")

        var packageName = view.findViewById<EditText>(R.id.edtPackageName)
        var packageDescription = view.findViewById<EditText>(R.id.edtPackageDescription)

        var packageServedFor = view.findViewById<Spinner>(R.id.selectPackageServedFor)
        packageServedFor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                packageServedForTxt = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        var packageIncludingItems = view.findViewById<EditText>(R.id.edtIncloodingPackageItem)
        var packagePrice = view.findViewById<EditText>(R.id.edtPackagePrice)

        databaseReferencePackage = FirebaseDatabase.getInstance().getReference("hotel/${username.toString()}/hotelPackages")
        databaseReferencePackage.child(packageID.toString()).get().addOnSuccessListener {
            if (it.exists()) {
                var packNameFromDB = it.child("packageName").value.toString()
                var packDescriptionFromDB = it.child("packageDescription").value.toString()
                var packPriceFromDB = it.child("packagePrice").value.toString()
                var packServedForFromDB = it.child("packageServedFor").value.toString()
                var packIncludingItemsFromDB = it.child("packageIncludingItems").value.toString()

                packageName.setText("$packNameFromDB")
                packageDescription.setText("$packDescriptionFromDB")
                packagePrice.setText("$packPriceFromDB")
                packageIncludingItems.setText("$packIncludingItemsFromDB")
                val adapterPayFor = ArrayAdapter.createFromResource(
                    requireContext(),
                    R.array.served_person,
                    android.R.layout.simple_spinner_item
                )
                packageServedFor.adapter = adapterPayFor

                if (packServedForFromDB == "1 Person") {
                    packageServedFor.setSelection(0)
                } else if (packServedForFromDB == "2 Persons") {
                    packageServedFor.setSelection(1)
                } else if (packServedForFromDB == "3 Persons") {
                    packageServedFor.setSelection(2)
                } else if (packServedForFromDB == "4 Persons") {
                    packageServedFor.setSelection(3)
                } else if (packServedForFromDB == "6 Persons") {
                    packageServedFor.setSelection(4)
                } else if (packServedForFromDB == "10 Persons") {
                    packageServedFor.setSelection(5)
                } else if (packServedForFromDB == "Other") {
                    packageServedFor.setSelection(6)
                }
            }
        }.addOnFailureListener {

        }


        var btnPackageSubmit = view.findViewById<Button>(R.id.btnSubmitPackage)
        btnPackageSubmit.setOnClickListener {

            var packageID = packageName.text.toString().replace(" ","")

            val dataSrt = mapOf<String,String>(
                "packageName" to packageName.text.toString(),
                "packageDescription" to packageDescription.text.toString(),
                "packagePrice" to packagePrice.text.toString(),
                "packageServedFor" to packageServedForTxt,
                "packageIncludingItems" to packageIncludingItems.text.toString(),
            )


            if(packageDescription.text.isNotEmpty() && packagePrice.text.isNotEmpty()){
                updatePack(dataSrt,packageID,username)

            }

            else{
                Toast.makeText(context, "Please Fill All Fields", Toast.LENGTH_SHORT).show()
            }


        }

        return view
    }

    private fun updatePack(dataSrt: Map<String, String>, packageID: String, username: String?) {
        databaseReferencePackage = FirebaseDatabase.getInstance().getReference("hotel/${username.toString()}/hotelPackages")

        databaseReferencePackage.child(packageID.toString()).updateChildren(dataSrt).addOnSuccessListener {
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show()


        }.addOnFailureListener {

        }
    }
}