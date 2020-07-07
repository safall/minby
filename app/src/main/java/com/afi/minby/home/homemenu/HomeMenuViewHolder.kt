package com.afi.minby.home.homemenu

import android.annotation.SuppressLint
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.afi.minby.R
import kotlinx.android.synthetic.main.home_menu_item.view.*

class HomeMenuViewHolder(val view: View, private val itemCallback: AdapterCallback) :
    RecyclerView.ViewHolder(view) {
    private lateinit var data: HomeMenuItem

    private val onItemSelected = View.OnClickListener {
        itemCallback.onItemClicked(data)
    }

    fun bind(items: HomeMenuItem) {
        data = items
        view.icon.setImageDrawable(ContextCompat.getDrawable(view.context, items.iconID))
        view.title.text = items.title
        view.subtitle.text = items.desc
        itemView.setOnClickListener(onItemSelected)
    }
}
