package com.afi.minby.core

import android.content.Context
import com.afi.minby.R

object ArtworkUtils {

    fun getSize(dimen: Int, marginDimen: Int, context: Context): Int {
        return getSize(
            dimen,
            marginDimen, context.screenOrientation, context
        )
    }

    fun getSize(dimen: Int, marginDimen: Int, requestedOrientation: Int, context: Context): Int {
        val pixelSize: Int = context.getDimensionPixelSize(dimen)
        val marginPixelSize = if (marginDimen != 0) context.getDimensionPixelSize(marginDimen) else 0
        if (pixelSize > 0) {
            return pixelSize - marginPixelSize
        }
        var widthPixels: Int = context.screenWidthPixels
        var heightPixels: Int =  context.screenHeightPixels
        if (requestedOrientation != context.screenOrientation) {
            val navigationBarHeight: Int = getNavigationBarHeight(context)
            widthPixels =
               context.screenHeightPixels + navigationBarHeight
            heightPixels =
                context.screenWidthPixels - navigationBarHeight
        }
        when (dimen) {
            R.dimen.size_screen_height -> {
                return heightPixels - marginPixelSize
            }
            R.dimen.size_screen_width -> {
                return widthPixels - marginPixelSize
            }
            R.dimen.size_screen_half_width -> {
                return (widthPixels - marginPixelSize) / 2
            }
            R.dimen.size_screen_one_quarter_width -> {
                return (widthPixels - marginPixelSize) / 4
            }
            else -> return 1
        }
    }

    private fun getNavigationBarHeight(context: Context): Int {
        return context.getAndroidDimensionPixelSize(
            "navigation_bar_height"
        )
    }
}