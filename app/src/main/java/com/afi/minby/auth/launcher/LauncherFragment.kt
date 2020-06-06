package com.afi.minby.auth.launcher

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.afi.minby.R
import kotlinx.android.synthetic.main.activity_launcher.*

class LauncherFragment : Fragment(R.layout.launcher_fragment) {

    private lateinit var viewModel: LauncherViewModel

    private val navigateToLogin = {
        NavHostFragment.findNavController(host_fragment).navigate(R.id.launcherToLoginFragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LauncherViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.handler.postDelayed(navigateToLogin, 2000)
    }

    override fun onDestroyView() {
        requireView().handler.removeCallbacks(navigateToLogin)
        super.onDestroyView()
    }
}
