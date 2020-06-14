package com.afi.minby.core

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.afi.minby.R

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
            outRect.bottom =
                parent.context.resources.getDimension(R.dimen.category_item_bottom_spacing).toInt()
            outRect.right =
                parent.context.resources.getDimension(R.dimen.category_item_horizontal_spacing).toInt()
            outRect.left =
                parent.context.resources.getDimension(R.dimen.category_item_horizontal_spacing).toInt()
        }
    }
}
