package com.afi.minby.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.home_menu_item.view.*

class HomeMenuViewHolder(val view: View, private val itemCallback: AdapterCallback) :
    RecyclerView.ViewHolder(view) {
    private lateinit var data: HomeMenuItem

    private val onItemSelected = View.OnClickListener {
        itemCallback.onItemClicked(data)
    }

    fun bind(items: HomeMenuItem) {
        data = items
        view.title.text = items.title
        view.desc.text = items.desc
        itemView.setOnClickListener(onItemSelected)
    }
}
