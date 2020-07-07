package com.afi.minby.home.feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.afi.minby.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_feed.*

@AndroidEntryPoint
class FeedFragment : Fragment(R.layout.fragment_feed) {

    private val viewModel: FeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchFeed()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.feedItems.observe(viewLifecycleOwner, Observer {
            recyclerView.visibility = View.GONE
        })
    }
}
