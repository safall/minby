package com.afi.minby.categories.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.afi.minby.categories.model.Category
import kotlinx.android.synthetic.main.submit_button_item.view.*

class SubmitButtonViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: Category) {
        view.submitButton.text = item.name
    }
}
