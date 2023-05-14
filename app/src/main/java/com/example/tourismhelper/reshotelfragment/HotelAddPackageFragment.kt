package com.example.tourismhelper.reshotelfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.tourismhelper.R
import com.example.tourismhelper.database.HotelPackageData
import com.example.tourismhelper.database.HotelRoomData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HotelAddPackageFragment : Fragment() {

    private lateinit var databaseReferencePackage: DatabaseReference
    private lateinit var packageServedForTxt: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hotel_add_package, container, false)

        var username = arguments?.getString("userName")

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

        var btnPackageSubmit = view.findViewById<Button>(R.id.btnSubmitPackage)
        btnPackageSubmit.setOnClickListener {

            var packageID = packageName.text.toString().replace(" ","")

            if(packageID.isNotEmpty()) {
                databaseReferencePackage =
                    FirebaseDatabase.getInstance().getReference("hotel/$username/hotelPackages")
                val user = HotelPackageData(
                    packageName.text.toString(),
                    packageDescription.text.toString(),
                    packageServedForTxt,
                    packageIncludingItems.text.toString(),
                    packagePrice.text.toString()
                )
                databaseReferencePackage.child(packageID).setValue(user).addOnSuccessListener {
                    Toast.makeText(context, "Package is Added Successfully", Toast.LENGTH_SHORT).show()
                    packageName.text.clear()
                    packageDescription.text.clear()
                    packagePrice.text.clear()
                    packageIncludingItems.text.clear()

                    var userNameBundle = Bundle()
                    userNameBundle.putString("userName", username)

                    val hotelIncomeFragment = ResHotelIncomeFragment()
                    hotelIncomeFragment.arguments = userNameBundle
                    parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel, hotelIncomeFragment).commit()

                }.addOnFailureListener {
                    Toast.makeText(context, "Failed to Add, Try Again", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(context, "Please Enter a Valid Package Name", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}