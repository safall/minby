package com.afi.minby.sendidea.summarydetails

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.afi.minby.R
import com.afi.minby.model.IdeaTemplateImpl
import com.afi.minby.sendidea.categories.model.CategoriesEnum
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_send_idea.*
import kotlinx.android.synthetic.main.summary_details_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class SummaryDetailsFragment : Fragment(R.layout.summary_details_fragment) {

    @Inject
    lateinit var template: IdeaTemplateImpl

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        description.text = template.ideaTemplate.description
        category.text = template.ideaTemplate.category.name
        category.setBackgroundColor(template.ideaTemplate.category.color)
        location.text = template.ideaTemplate.latlng.toString()
        userEmail.text = "abc@gmail.com"

        submitButton.setOnClickListener {
            NavHostFragment.findNavController(host_fragment)
                .navigate(R.id.summaryDetailsFragmentToThankYouFragment)
        }
    }


    private fun CategoriesEnum.getDrawableForCategory(): Drawable? {
        return ContextCompat.getDrawable(
            requireContext(), when (this) {
                CategoriesEnum.NATURE -> R.drawable.ic_nature
                CategoriesEnum.TECHNOLOGY -> R.drawable.ic_technology
                CategoriesEnum.BUILDING -> R.drawable.ic_city
                CategoriesEnum.SOCIAL -> R.drawable.ic_social
                CategoriesEnum.ACTIVITIES -> R.drawable.ic_activities
                CategoriesEnum.ORGANISATION -> R.drawable.ic_organization
                CategoriesEnum.OTHER -> 0
            }
        )
    }
}
