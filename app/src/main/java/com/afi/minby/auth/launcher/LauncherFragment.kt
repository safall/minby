package com.afi.minby.auth.launcher

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.afi.minby.R
import kotlinx.android.synthetic.main.activity_launcher.*

class LauncherFragment : Fragment(R.layout.launcher_fragment) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel: LauncherViewModel by viewModels()
        viewModel.loadHomePage.observe(viewLifecycleOwner, Observer {
            NavHostFragment.findNavController(host_fragment).navigate(R.id.launcherToLoginFragment)
        })
        viewModel.delay()
    }
}
