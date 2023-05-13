package com.example.tourismhelper.tourfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tourismhelper.R

class TouristReviewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tourist_review, container, false

            ReviewPersonName = (EditText) rootView.findViewById(R.id.ReviewPersonName)
            ReviewEmail = (EditText) rootView.findViewById(R.id.ReviewEmail)
            ReviewComment = (EditText) rootView.findViewById(R.id.ReviewComment)
            Reviewsubmit = (Button) rootView.findViewById(R.id.Reviewsubmit)

            Reviewsubmit.setOnClickListner(new View.OnClickListner() {
                @Override
                public void onClick(View v) {
                    String first = ReviewPersonName.getText().toString()
                    String comment = ReviewComment.getText().toString()

                    Bundle bundle = new Bundle()
                    bundle.putString("Name",first)
                    bundle.putString("Review",comment)

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager()
                    FragmentTransaction fragmenetTransaction = FragmentManager.beginTransaction()

                    TouristReviewShowFragment tourreviewshowfragment = new TouristReviewShowFragment
                    tourreviewshowfragment.setArguments(bundle)

                    fragmentTransaction.replace(R.id.   ,tourreviewshowfragment)
                    fragmentTransaction.commit()
                }
        })


    }
}