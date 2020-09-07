package com.afi.minby.settings

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SettingsItemDecoration @JvmOverloads constructor(
    private val horizontalSpacingPx: Int,
    private val addSpacingToFirst: Boolean = false
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.right = horizontalSpacingPx
        if (addSpacingToFirst && parent.getChildAdapterPosition(view) == 0) {
            outRect.left = horizontalSpacingPx
        }
    }
}
