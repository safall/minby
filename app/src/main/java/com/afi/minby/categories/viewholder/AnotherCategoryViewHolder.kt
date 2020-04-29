package com.afi.minby.categories.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.afi.minby.categories.model.Category
import com.afi.minby.home.AdapterCallback
import kotlinx.android.synthetic.main.another_category_item.view.*

class AnotherCategoryViewHolder(val view: View, private val itemCallback: AdapterCallback) :
    RecyclerView.ViewHolder(view) {
    private lateinit var data: Category
    private val onItemSelected = View.OnClickListener {
        itemCallback.onItemClicked(data)
    }

    fun bind(item: Category) {
        data = item
        view.anotherButton.text = "Annet"
        itemView.setOnClickListener(onItemSelected)
    }
}
