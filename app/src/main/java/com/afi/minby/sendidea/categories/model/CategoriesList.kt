package com.afi.minby.sendidea.categories.model

import android.content.Context
import androidx.core.content.ContextCompat.getColor
import com.afi.minby.R
import com.afi.minby.core.StringUtils.getString

object CategoriesList {

    fun getCategories(context: Context): List<Category> {
        return arrayListOf<Category>().apply {
            add(
                0,
                Category(
                    getString(R.string.social, context),
                    CategoriesEnum.SOCIAL,
                    getColor(context, R.color.social)
                )
            )
            add(
                1,
                Category(
                    getString(R.string.building_and_construction, context),
                    CategoriesEnum.BUILDING,
                    getColor(context, R.color.building_and_construction)
                )
            )
            add(
                2,
                Category(
                    getString(R.string.nature_and_green, context),
                    CategoriesEnum.NATURE,
                    getColor(context, R.color.nature_and_green)

                )
            )
            add(
                3,
                Category(
                    getString(R.string.activities_and_training, context),
                    CategoriesEnum.ACTIVITIES,
                    getColor(context, R.color.activities_and_training)
                )
            )
            add(
                4,
                Category(
                    getString(R.string.technology, context),
                    CategoriesEnum.TECHNOLOGY,
                    getColor(context, R.color.technology)

                )
            )
            add(
                5,
                Category(
                    getString(R.string.organisation, context),
                    CategoriesEnum.ORGANISATION,
                    getColor(context, R.color.organisation)
                )
            )
            add(
                6,
                Category(
                    getString(R.string.another, context),
                    CategoriesEnum.OTHER,
                    getColor(context, R.color.white)
                )
            )
        }
    }
}