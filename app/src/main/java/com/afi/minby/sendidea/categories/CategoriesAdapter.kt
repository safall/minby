package com.afi.minby.sendidea.categories

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afi.minby.R
import com.afi.minby.core.inflate
import com.afi.minby.home.homemenu.AdapterCallback
import com.afi.minby.sendidea.categories.model.Category
import com.afi.minby.sendidea.categories.viewholder.AnotherCategoryViewHolder
import com.afi.minby.sendidea.categories.viewholder.CategoryItemViewHolder

class CategoriesAdapter(var items: List<Category>, private var itemCallback: AdapterCallback) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_HOLDER_ANOTHER_CATEGORY = 0
        const val VIEW_HOLDER_CATEGORY = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_HOLDER_ANOTHER_CATEGORY -> AnotherCategoryViewHolder(
                parent.context.inflate(R.layout.another_category_item),
                itemCallback
            )
            VIEW_HOLDER_CATEGORY ->
                CategoryItemViewHolder(
                    parent.context.inflate(
                        R.layout.category_item,
                        parent,
                        false
                    ), itemCallback
                )
            else -> throw IllegalArgumentException("No such view type found")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            items.size - 1 -> VIEW_HOLDER_ANOTHER_CATEGORY
            else -> VIEW_HOLDER_CATEGORY
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CategoryItemViewHolder -> holder.bind(items[position])
            is AnotherCategoryViewHolder -> holder.bind(items[position])
        }
    }
}
