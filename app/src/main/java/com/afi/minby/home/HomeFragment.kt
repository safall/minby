package com.afi.minby.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.afi.minby.R
import com.afi.minby.core.VerticalSpaceDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.home_fragment.*

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.addHomeMenuItems()
        observeHomeMenuItems()
    }

    private fun observeHomeMenuItems() {
        viewModel.gethomeMenuItem_.observe(viewLifecycleOwner, Observer {
            with(recyclerView) {
                addItemDecoration(VerticalSpaceDecoration())
                adapter = HomeMenuAdapter(it, object : AdapterCallback {
                    override fun <T> onItemClicked(item: T) {
                        NavHostFragment.findNavController(hostFragment)
                            .navigate(R.id.sendIdeaActivity)
                    }
                })
            }
        })
    }
}
