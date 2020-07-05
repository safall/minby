package com.afi.minby.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afi.minby.R

class HomeMenuAdapter internal constructor(
    var items: List<HomeMenuItem>,
    private var itemCallback: AdapterCallback
) :
    RecyclerView.Adapter<HomeMenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMenuViewHolder {
        return HomeMenuViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.home_menu_item,
                parent,
                false
            ), itemCallback
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: HomeMenuViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
