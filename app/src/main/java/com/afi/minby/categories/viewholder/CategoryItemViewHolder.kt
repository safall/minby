package com.afi.minby.categories.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.afi.minby.categories.model.Category
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryItemViewHolder(val view: View, val artworkSize: Int) : RecyclerView.ViewHolder(view) {

    fun bind(item: Category) {
        view.categoryName.text = "socialt"
        setWidth(artworkSize)
    }

    fun setWidth(width: Int) {
        val layoutParams = itemView.layoutParams
        layoutParams.width = width
        view.layoutParams = layoutParams
    }

}