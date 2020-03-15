package com.afi.minby.categories

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afi.minby.R
import com.afi.minby.categories.model.Category
import com.afi.minby.categories.viewholder.AnotherCategoryViewHolder
import com.afi.minby.categories.viewholder.CategoryItemViewHolder
import com.afi.minby.categories.viewholder.SubmitButtonViewHolder
import com.afi.minby.core.inflate

class CategoriesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = listOf<Category>()

    companion object {
        const val VIEW_HOLDER_SUBMIT_BUTTON = 1
        const val VIEW_HOLDER_ANOTHER_CATEGORY = 2
        const val VIEW_HOLDER_CATEGORY = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_HOLDER_SUBMIT_BUTTON -> SubmitButtonViewHolder(parent.context.inflate(R.layout.submit_button_item))
            VIEW_HOLDER_ANOTHER_CATEGORY -> AnotherCategoryViewHolder(parent.context.inflate(R.layout.another_category_item))
            VIEW_HOLDER_CATEGORY -> CategoryItemViewHolder(parent.context.inflate(R.layout.category_item))
            else -> throw IllegalArgumentException("No such view type found")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            items.size - 1 -> VIEW_HOLDER_SUBMIT_BUTTON
            items.size - 2 -> VIEW_HOLDER_ANOTHER_CATEGORY
            else -> VIEW_HOLDER_CATEGORY
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SubmitButtonViewHolder -> holder.bind(items[position])
            is CategoryItemViewHolder -> holder.bind(items[position])
            is AnotherCategoryViewHolder -> holder.bind(items[position])
        }
    }
}
