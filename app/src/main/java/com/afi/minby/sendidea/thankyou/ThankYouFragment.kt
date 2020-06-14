package com.afi.minby.sendidea.thankyou

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.afi.minby.R
import kotlinx.android.synthetic.main.final_thankyou_view.*

class ThankYouFragment : Fragment(R.layout.final_thankyou_view) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        finishButton.setOnClickListener {
            activity?.finish()
        }
    }
}