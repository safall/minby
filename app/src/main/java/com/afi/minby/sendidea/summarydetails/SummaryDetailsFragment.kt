package com.afi.minby.sendidea.summarydetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.afi.minby.R
import com.afi.minby.sendidea.categories.model.CategoriesList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_send_idea.*
import kotlinx.android.synthetic.main.summary_details_fragment.*

@AndroidEntryPoint
class SummaryDetailsFragment : Fragment(R.layout.summary_details_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        description.text = arguments?.getString("ARG_DESCRIPTION")
        category.text = CategoriesList.getCategories(requireContext())[0].name
        category.setBackgroundColor(CategoriesList.getCategories(requireContext())[0].color)
        location.text = "Rolf E. Stenersens alle 38B"
        userEmail.text = "abc@gmail.com"

        submitButton.setOnClickListener {
            NavHostFragment.findNavController(host_fragment)
                .navigate(R.id.summaryDetailsFragmentToThankYouFragment)
        }
    }
}
