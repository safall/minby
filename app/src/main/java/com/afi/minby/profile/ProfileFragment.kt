package com.afi.minby.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.afi.minby.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.profile_fragment.*

class ProfileFragment : Fragment(R.layout.profile_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userName.text = "John Doe"
        userAddress.text = "Hollywood park, TX"
        userEmail.text = "john.doe@johndoe.com"
        points.text = "84"

        editProfileButton.setOnClickListener {
            NavHostFragment.findNavController(hostFragment).navigate(R.id.editProfileDialog)
        }
    }
}
