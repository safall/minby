package com.afi.minby.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afi.minby.R
import com.afi.minby.core.inflate

class HomeMenuAdapter : RecyclerView.Adapter<HomeMenuViewHolder>() {

    var items = listOf<HomeMenuItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMenuViewHolder {
        return HomeMenuViewHolder(parent.context.inflate(R.layout.home_menu_item))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: HomeMenuViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
