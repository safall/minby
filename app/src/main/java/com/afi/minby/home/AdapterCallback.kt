package com.afi.minby.home

interface AdapterCallback {
    fun <T> onItemClicked(item: T)
}