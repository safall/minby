package com.afi.minby.model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class MyClusterItem(
    val lat: Double,
    val lng: Double,
    val itemPosition: LatLng,
    val itemTitle: String,
    val itemSnippet: String
) : ClusterItem {
    override fun getSnippet() = itemSnippet

    override fun getTitle() = itemTitle

    override fun getPosition() = itemPosition
}
