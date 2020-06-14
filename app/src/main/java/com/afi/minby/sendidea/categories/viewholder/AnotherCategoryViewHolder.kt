package com.afi.minby.sendidea.categories.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.afi.minby.sendidea.categories.model.Category
import kotlinx.android.synthetic.main.another_category_item.view.*

class AnotherCategoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: Category) {
        view.anotherButton.text = item.name
    }
}
