package com.afi.minby.categories.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.afi.minby.categories.model.Category
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: Category) {
        view.categoryName.text = "socialt"
    }
}
