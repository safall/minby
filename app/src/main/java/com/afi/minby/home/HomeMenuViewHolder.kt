package com.afi.minby.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.home_menu_item.view.*

class HomeMenuViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(items: HomeMenuItem) {
        view.title.text = items.title
        view.desc.text = items.desc
    }
}
