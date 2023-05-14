package com.example.tourismhelper.reshotelfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.tourismhelper.R
import com.example.tourismhelper.ResHotelLoginActivity
import com.example.tourismhelper.database.HotelRoomData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HotelAddRoomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HotelAddRoomFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var databaseReferenceRoom: DatabaseReference
    private lateinit var roomForTxt: String
    private lateinit var roomPayForTxt: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_hotel_add_room, container, false)
        activity?.title ="Add New Room"

        var username = arguments?.getString("userName")

        var roomName = view.findViewById<EditText>(R.id.edtRoomName)
        var roomDescription = view.findViewById<EditText>(R.id.edtRoomDescription)

        var roomFor = view.findViewById<Spinner>(R.id.selectRoomFor)
        roomFor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                roomForTxt = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        var roomPayFor = view.findViewById<Spinner>(R.id.selectPayFor)
        roomPayFor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                roomPayForTxt = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        var roomPrice = view.findViewById<EditText>(R.id.edtRoomPrice)

        //room conditions
        var AC = view.findViewById<CheckBox>(R.id.cbAC)
        var fan = view.findViewById<CheckBox>(R.id.cbFan)
        var WiFi = view.findViewById<CheckBox>(R.id.cbWiFi)
        var TV = view.findViewById<CheckBox>(R.id.cbTV)
        var hotWater = view.findViewById<CheckBox>(R.id.cbHotWater)
        var balcony = view.findViewById<CheckBox>(R.id.cbBalcony)

        var btnResStartMain = view.findViewById<Button>(R.id.btnSubmitRoom)
        btnResStartMain.setOnClickListener {

            var roomID = roomName.text.toString().replace(" ","")

            if(roomID.isNotEmpty()) {
                databaseReferenceRoom =
                    FirebaseDatabase.getInstance().getReference("hotel/$username/hotelRooms")
                val user = HotelRoomData(
                    roomName.text.toString(),
                    roomDescription.text.toString(),
                    roomForTxt,
                    roomPayForTxt,
                    roomPrice.text.toString(),
                    AC.isChecked,
                    fan.isChecked,
                    WiFi.isChecked,
                    TV.isChecked,
                    hotWater.isChecked,
                    balcony.isChecked
                )
                databaseReferenceRoom.child(roomID).setValue(user).addOnSuccessListener {
                    Toast.makeText(context, "Room is Added Successfully", Toast.LENGTH_SHORT).show()
                    roomName.text.clear()
                    roomDescription.text.clear()
                    roomPrice.text.clear()
                    AC.isChecked.not()
                    fan.isChecked.not()
                    WiFi.isChecked.not()
                    TV.isChecked.not()
                    hotWater.isChecked.not()
                    balcony.isChecked.not()

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
                Toast.makeText(context, "Enter a Valid Room Name", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HotelAddRoomFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HotelAddRoomFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}