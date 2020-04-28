package com.afi.minby.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.afi.minby.R
import com.afi.minby.core.VerticalSpaceDecoration
import com.afi.minby.di.MinByApplication
import javax.inject.Inject
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment(R.layout.home_fragment) {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: HomeViewModel
    private val homeMenuAdapter = HomeMenuAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MinByApplication.instance.component.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        viewModel.addHomeMenuItems()
        this.observeHomeMenuItems()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(recyclerView) {
            addItemDecoration(VerticalSpaceDecoration())
            adapter = homeMenuAdapter
        }
    }

    private fun observeHomeMenuItems() {
        viewModel.homeMenuItem_.observe(this, Observer {
            addItem(it)
        })

        viewModel.isEmpty.observe(this, Observer {
        })

        viewModel.showError.observe(this, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            println("show error message $it")
        })
    }
    private fun addItem(items: List<HomeMenuItem>) {
        homeMenuAdapter.items = items
        homeMenuAdapter.notifyDataSetChanged()
    }
}
