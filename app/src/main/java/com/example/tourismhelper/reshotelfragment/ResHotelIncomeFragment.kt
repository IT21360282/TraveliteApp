package com.example.tourismhelper.reshotelfragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.tourismhelper.HotelProfileViewActivity
import com.example.tourismhelper.R
import com.example.tourismhelper.ResHotelDisplayListActivity
import com.example.tourismhelper.ResHotelMainActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResHotelIncomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResHotelIncomeFragment : Fragment() {
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

    private lateinit var getContent: ActivityResultLauncher<String>
    private lateinit var imageUri: Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View ?{
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_res_hotel_income, container, false)

        var username = arguments?.getString("userName")

        val btnAddRoom = view?.findViewById<Button>(R.id.addHotelRoom)
        btnAddRoom?.setOnClickListener {
            var userNameBundle = Bundle()
            userNameBundle.putString("userName", username)

            val addRoomFragment = HotelAddRoomFragment()
            addRoomFragment.arguments = userNameBundle
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel, addRoomFragment).commit()
        }
        val btnAddMeal = view?.findViewById<Button>(R.id.addHotelMeal)
        btnAddMeal?.setOnClickListener {
            var userNameBundle = Bundle()
            userNameBundle.putString("userName", username)

            val addMealFragment = HotelAddMealFragment()
            addMealFragment.arguments = userNameBundle
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel, addMealFragment).commit()
        }
        val btnAddPackage = view?.findViewById<Button>(R.id.addHotelPackage)
        btnAddPackage?.setOnClickListener {
            var userNameBundle = Bundle()
            userNameBundle.putString("userName", username)

            val addPackageFragment = HotelAddPackageFragment()
            addPackageFragment.arguments = userNameBundle
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerResHotel, addPackageFragment).commit()
        }

        val btnViewYourHotelProfile = view.findViewById<Button>(R.id.viewYourHotelProfileBtn)
        btnViewYourHotelProfile.setOnClickListener{
            val intent = Intent(context, ResHotelDisplayListActivity::class.java)
            intent.putExtra("userName", username)
            startActivity(intent)
        }

        val btnPickImage = view.findViewById<Button>(R.id.btnPickImage)
        val pikedImg = view.findViewById<ImageView>(R.id.pikedImg)

        getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                imageUri = uri
                pikedImg.setImageURI(uri)
            }
        }

        btnPickImage.setOnClickListener {
            getContent.launch("image/*")
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
         * @return A new instance of fragment ResHotelIncomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ResHotelIncomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}