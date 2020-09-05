package com.afi.minby.sendidea.categories.viewholder

import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.afi.minby.R
import com.afi.minby.home.homemenu.AdapterCallback
import com.afi.minby.sendidea.categories.model.CategoriesEnum
import com.afi.minby.sendidea.categories.model.Category
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryItemViewHolder(val view: View, private val itemCallback: AdapterCallback) :
    RecyclerView.ViewHolder(view) {

    fun bind(item: Category) {
        with(view) {
            with(categoryButton) {
                text = item.name
                background.setTint(item.color)
                setCompoundDrawablesWithIntrinsicBounds(
                    item.iconId.getDrawableForCategory(),
                    null,
                    null,
                    null
                )
            }
            setOnClickListener {
                itemCallback.onItemClicked(item)
            }
        }
    }

    private fun CategoriesEnum.getDrawableForCategory(): Drawable? {
        return ContextCompat.getDrawable(
            view.context, when (this) {
                CategoriesEnum.NATURE -> R.drawable.ic_nature
                CategoriesEnum.TECHNOLOGY -> R.drawable.ic_technology
                CategoriesEnum.BUILDING -> R.drawable.ic_city
                CategoriesEnum.SOCIAL -> R.drawable.ic_social
                CategoriesEnum.ACTIVITIES -> R.drawable.ic_activities
                CategoriesEnum.ORGANISATION -> R.drawable.ic_organization
                CategoriesEnum.OTHER -> 0
            }
        )
    }
}
