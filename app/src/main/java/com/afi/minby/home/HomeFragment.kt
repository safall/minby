package com.afi.minby.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.afi.minby.R
import com.afi.minby.core.VerticalSpaceDecoration
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment(R.layout.home_fragment) {

    private lateinit var viewModel: HomeViewModel
    private val homeMenuAdapter = HomeMenuAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(recyclerView) {
            addItemDecoration(VerticalSpaceDecoration())
            adapter = homeMenuAdapter
        }
        addItems()
    }

    private fun addItems() {
        val menuItems = arrayListOf<HomeMenuItem>()
        menuItems.apply {
            add(
                0,
                HomeMenuItem(
                    "Send oss en ide",
                    "Lorem ipsum dolor sit amet consectetur adipiscing elit. praesent bibendum massa lectus, vitae",
                    "idea"
                )
            )
            add(
                1,
                HomeMenuItem(
                    "Send oss en vurdering",
                    "Lorem ipsum dolor sit amet consectetur adipiscing elit. praesent bibendum massa lectus, vitae",
                    "vurdering"
                )
            )
            add(
                2,
                HomeMenuItem(
                    "Send oss en intervju",
                    "Lorem ipsum dolor sit amet consectetur adipiscing elit. praesent bibendum massa lectus, vitae",
                    "intervju"
                )
            )
        }

        homeMenuAdapter.items = menuItems
        homeMenuAdapter.notifyDataSetChanged()
    }
}
