package com.afi.minby.settings

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afi.minby.R
import com.afi.minby.core.inflate
import com.afi.minby.home.homemenu.AdapterCallback
import kotlinx.android.synthetic.main.settings_item_text.view.*

class SettingsAdapter(var items: List<SettingsItem>, private var itemCallback: AdapterCallback) :
    RecyclerView.Adapter<SettingsAdapter.SettingsItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsItemViewHolder {
        return SettingsItemViewHolder(
            parent.context.inflate(R.layout.settings_item_text, parent, false),
            itemCallback
        )
    }

    override fun onBindViewHolder(holder: SettingsItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class SettingsItemViewHolder(val view: View, private val itemCallback: AdapterCallback) :
        RecyclerView.ViewHolder(view) {
        fun bind(item: SettingsItem) {
            itemView.title.text = itemView.context.getText(item.title)
            itemView.setOnClickListener {
                itemCallback.onItemClicked(item)
            }
        }
    }
}
