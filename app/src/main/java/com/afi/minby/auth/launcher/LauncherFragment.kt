package com.afi.minby.auth.launcher

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.afi.minby.R
import kotlinx.android.synthetic.main.activity_launcher.*

class LauncherFragment : Fragment() {

    companion object {
        fun newInstance() = LauncherFragment()
    }

    private lateinit var viewModel: LauncherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.launcher_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LauncherViewModel::class.java)
        Handler().postDelayed({
            NavHostFragment.findNavController(host_fragment).navigate(R.id.launcherToLoginFragment)
        }, 2000)
    }
}
