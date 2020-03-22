package com.afi.minby.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.afi.minby.R
import com.afi.minby.message.viewmodel.MessageViewModel

class MessageFragment : Fragment(R.layout.message_fragment) {

    private lateinit var viewModel: MessageViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MessageViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
