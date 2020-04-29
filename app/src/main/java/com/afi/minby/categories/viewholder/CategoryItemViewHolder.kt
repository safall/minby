package com.afi.minby.categories.viewholder

import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.afi.minby.R
import com.afi.minby.categories.model.CategoriesEnum
import com.afi.minby.categories.model.Category
import com.afi.minby.home.AdapterCallback
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryItemViewHolder(
    val view: View,
    val artworkSize: Int,
    private val itemCallback: AdapterCallback
) : RecyclerView.ViewHolder(view) {
    private lateinit var data: CategoriesEnum
    private val onItemSelected = View.OnClickListener {
        itemCallback.onItemClicked(data)
    }

    fun bind(item: Category) {
        view.categoryName.text = item.name
        setWidth(artworkSize)
        view.categoryImage.setImageDrawable(item.iconId?.let { getIconFromDrawable(it) }?.let {
            ContextCompat.getDrawable(
                view.context,
                it
            )
        })
        itemView.setOnClickListener(onItemSelected)
    }

    @DrawableRes
    private fun getIconFromDrawable(categoriesEnum: CategoriesEnum): Int {
        data = categoriesEnum
        return when (categoriesEnum) {
            CategoriesEnum.NATURE -> R.drawable.ic_nature
            CategoriesEnum.TECHNOLOGY -> R.drawable.ic_smartphone
            CategoriesEnum.BUILDING -> R.drawable.ic_city
            CategoriesEnum.SOCIAL -> R.drawable.ic_social
            CategoriesEnum.ACTIVITIES -> R.drawable.ic_activities
            CategoriesEnum.ORGANISATION -> R.drawable.ic_organisation
        }
    }

    fun setWidth(width: Int) {
        val layoutParams = itemView.layoutParams
        layoutParams.width = width
        view.layoutParams = layoutParams
    }
}
