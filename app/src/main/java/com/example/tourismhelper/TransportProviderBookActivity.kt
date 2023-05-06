package com.example.tourismhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.tourismhelper.transportDatabase.TransportBook
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class TransportProviderBookActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transport_provider_book)

        var bookTravelTourist = supportActionBar
        bookTravelTourist?.setTitle("Book Register")

        var touristTransportName = findViewById<EditText>(R.id.textView_bookName_Transport)
        var touristTransportAddress = findViewById<EditText>(R.id.textView_bookAddress_Transport)
        var touristTransportContact = findViewById<EditText>(R.id.textView_bookContact_Transport)
        var touristTransportNIC = findViewById<EditText>(R.id.textView_bookNIC_Transport)
        var touristTransportPlace = findViewById<EditText>(R.id.textView_bookPlace_Transport)
        var touristTransportDate = findViewById<EditText>(R.id.textView_bookDate_Transport)


        var transportBookSumbitBtn = findViewById<Button>(R.id.button_book_Transport)
        transportBookSumbitBtn.setOnClickListener {

            if(touristTransportName.text.toString().isNotEmpty()) {
                databaseReference =
                    FirebaseDatabase.getInstance().getReference("transport_book")
                var transportBook = TransportBook(
                    touristTransportName.text.toString(),
                    touristTransportAddress.text.toString(),
                    touristTransportContact.text.toString(),
                    touristTransportNIC.text.toString(),
                    touristTransportPlace.text.toString(),
                    touristTransportDate.text.toString()

                )

                databaseReference.child(touristTransportName.text.toString()).setValue(transportBook)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Successfully Booking", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(this, "Failed to Boking, Try Again", Toast.LENGTH_SHORT).show()
                    }
            }
            else{
                Toast.makeText(this, "Please Enter Valid Register Number", Toast.LENGTH_SHORT).show()
            }
        }

    }
}