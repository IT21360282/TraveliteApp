package com.example.tourismhelper.tourfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class TouristReviewShowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tourist_review_show, container, false)

        Bundle bundle = getArguments()

        String name = bundle.getString("Name")
        String comment = bundle.getString("review")

        TextView firstText = (TextView) rootView.findViewById(R.id.review2)
        TextView lastText = (TextView) rootView.findViewById(R.id.review4)

        firstText.setText(name)
        lastText.setText(comment)
    }
}