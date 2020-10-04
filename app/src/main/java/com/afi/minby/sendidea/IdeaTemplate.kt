package com.afi.minby.sendidea

import com.afi.minby.sendidea.categories.model.CategoriesEnum
import com.afi.minby.sendidea.categories.model.Category
import com.google.android.gms.maps.model.LatLng

data class IdeaTemplate(
    val ideaType: String = "",
    val category: Category = Category("", CategoriesEnum.ACTIVITIES, 0),
    val latlng: LatLng = LatLng(59.9139, 10.7522),
    val description: String = ""
)
