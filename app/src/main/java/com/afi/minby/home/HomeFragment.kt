package com.afi.minby.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.afi.minby.R
import com.afi.minby.core.VerticalSpaceDecoration
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment(R.layout.home_fragment) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeMenuAdapter: HomeMenuAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.addHomeMenuItems()
        observeHomeMenuItems()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeMenuAdapter = HomeMenuAdapter(object : AdapterCallback {
            override fun <T> onItemClicked(item: T) {
                NavHostFragment.findNavController(hostFragment)
                    .navigate(R.id.sendIdeaActivity)
            }
        })
        with(recyclerView) {
            addItemDecoration(VerticalSpaceDecoration())
            adapter = homeMenuAdapter
        }
    }

    private fun observeHomeMenuItems() {
        viewModel.gethomeMenuItem_.observe(viewLifecycleOwner, Observer {
            updateItems(it)
        })

        viewModel.isEmpty.observe(viewLifecycleOwner, Observer {
        })

        viewModel.showError.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            println("show error message $it")
        })
    }

    private fun updateItems(items: List<HomeMenuItem>) {
        homeMenuAdapter.items = items
        homeMenuAdapter.notifyDataSetChanged()
    }
}
