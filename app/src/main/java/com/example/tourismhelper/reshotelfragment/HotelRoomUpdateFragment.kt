package com.example.tourismhelper.reshotelfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.tourismhelper.R
import com.example.tourismhelper.database.HotelRoomData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HotelRoomUpdateFragment : Fragment() {

    private lateinit var databaseReferenceRoom: DatabaseReference
    private lateinit var roomForTxt: String
    private lateinit var roomPayForTxt: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hotel_room_update, container, false)

        var username = arguments?.getString("userName")
        var roomID = arguments?.getString("roomID")



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

        databaseReferenceRoom = FirebaseDatabase.getInstance().getReference("hotel/${username.toString()}/hotelRooms")
        databaseReferenceRoom.child(roomID.toString()).get().addOnSuccessListener {
            if(it.exists()){
                var roomNameFromDB = it.child("roomName").value.toString()
                var roomDescriptionFromDB = it.child("roomDescription").value.toString()
                var roomPriceFromDB = it.child("roomPrice").value.toString()
                var roomPayForFromDB = it.child("roomPayFor").value.toString()
                var roomForFromDB = it.child("roomFor").value.toString()
                var acRfomDB = it.child("ac").value.toString()
                var fanFromDB = it.child("fan").value.toString()
                var wifiFromDB = it.child("wiFi").value.toString()
                var tvFromDB = it.child("tv").value.toString()
                var hotWaterFromDB = it.child("hotWater").value.toString()
                var balconyFromDB = it.child("balcony").value.toString()
                //password = it.child("password").value.toString()

                if(acRfomDB=="true"){
                    AC.isChecked = true
                }
                if(fanFromDB=="true"){
                    fan.isChecked = true
                }
                if(wifiFromDB=="true"){
                    WiFi.isChecked = true
                }
                if(tvFromDB=="true"){
                    TV.isChecked = true
                }
                if(hotWaterFromDB=="true"){
                    hotWater.isChecked = true
                }
                if(balconyFromDB=="true"){
                    balcony.isChecked = true
                }


                roomName.setText("$roomNameFromDB")
                roomDescription.setText("$roomDescriptionFromDB")
                roomPrice.setText("$roomPriceFromDB")

                val adapterPayFor = ArrayAdapter.createFromResource(
                    requireContext(),
                    R.array.room_pay_for,
                    android.R.layout.simple_spinner_item
                )
                roomPayFor.adapter = adapterPayFor

                if(roomPayForFromDB=="Per Night"){
                    roomPayFor.setSelection(0)
                }
                else if(roomPayForFromDB=="Per Day"){
                    roomPayFor.setSelection(1)
                }
                else if(roomPayForFromDB=="Per Duration"){
                    roomPayFor.setSelection(2)
                }
                else if(roomPayForFromDB=="Other"){
                    roomPayFor.setSelection(3)
                }

                val adapterRoomFor = ArrayAdapter.createFromResource(
                    requireContext(),
                    R.array.room_person_val,
                    android.R.layout.simple_spinner_item
                )
                roomFor.adapter = adapterRoomFor

                if(roomForFromDB=="Single Person"){
                    roomFor.setSelection(0)
                }
                else if(roomForFromDB=="Couple"){
                    roomFor.setSelection(1)
                }
                else if(roomForFromDB=="Family(3 - 4 Persons)"){
                    roomFor.setSelection(2)
                }
                else if(roomForFromDB=="Other"){
                    roomFor.setSelection(3)
                }
            }
            else{
                //Toast.makeText(this, "User Does not Exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{

        }

        var btnUpdate = view.findViewById<Button>(R.id.btnSubmitRoom)
        btnUpdate.setOnClickListener {

            val dataSrt = mapOf<String,String>(
                "roomName" to roomName.text.toString(),
                "roomDescription" to roomDescription.text.toString(),
                "roomPrice" to roomPrice.text.toString(),
                "roomPayFor" to roomPayForTxt,
                "roomFor" to roomForTxt,
            )
            val dataBoolean = mapOf<String,Boolean>(
                "ac" to AC.isChecked,
                "fan" to fan.isChecked,
                "wiFi" to WiFi.isChecked,
                "tv" to TV.isChecked,
                "hotWater" to hotWater.isChecked,
                "balcony" to balcony.isChecked
            )

            if(roomDescription.text.isNotEmpty() && roomPrice.text.isNotEmpty()){
                updateRoom(dataSrt,dataBoolean,roomID,username)

            }

            else{
                Toast.makeText(context, "Please Fill All Fields", Toast.LENGTH_SHORT).show()
            }

        }


        return  view
    }

    private fun updateRoom(dataSrt: Map<String, String>, dataBoolean: Map<String, Boolean>, roomID: String?, username: String?) {
        databaseReferenceRoom = FirebaseDatabase.getInstance().getReference("hotel/${username.toString()}/hotelRooms")

        databaseReferenceRoom.child(roomID.toString()).updateChildren(dataSrt).addOnSuccessListener {
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show()


        }.addOnFailureListener {

        }
        databaseReferenceRoom.child(roomID.toString()).updateChildren(dataBoolean).addOnSuccessListener {
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show()


        }.addOnFailureListener {

        }
    }
}