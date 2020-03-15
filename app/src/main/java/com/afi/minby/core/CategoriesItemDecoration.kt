package com.afi.minby.core

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.afi.minby.R
import com.afi.minby.categories.CategoriesAdapter.Companion.VIEW_HOLDER_ANOTHER_CATEGORY
import com.afi.minby.categories.CategoriesAdapter.Companion.VIEW_HOLDER_CATEGORY
import com.afi.minby.categories.CategoriesAdapter.Companion.VIEW_HOLDER_SUBMIT_BUTTON

class CategoriesItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val itemPos = parent.getChildAdapterPosition(view)
        if (itemPos > -1) {
            val viewType = parent.adapter?.getItemViewType(itemPos)
            outRect.bottom = getSpacingSize(viewType, parent)
            outRect.right = parent.context.resources.getDimension(R.dimen.category_item_bottom_spacing).toInt()
            outRect.left = parent.context.resources.getDimension(R.dimen.category_item_bottom_spacing).toInt()
        }
    }

    private fun getSpacingSize(viewType: Int?, parent: RecyclerView): Int {
        return when (viewType) {
            VIEW_HOLDER_CATEGORY -> parent.context.resources.getDimension(R.dimen.category_item_bottom_spacing).toInt()
            VIEW_HOLDER_ANOTHER_CATEGORY -> parent.context.resources.getDimension(R.dimen.another_category_itembottom_spacing).toInt()
            VIEW_HOLDER_SUBMIT_BUTTON -> parent.context.resources.getDimension(R.dimen.recyclerview_spacing).toInt()
            else -> throw IllegalArgumentException("No such view type found")
        }
    }
}
