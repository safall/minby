package com.afi.minby.core

import android.graphics.Color

object ColorUtils {

    fun parseColor(hexString: String): Int? {
        val value = if (hexString.startsWith("#")) hexString else ("#$hexString")
        return try {
            Color.parseColor(value)
        } catch (ex: Exception) {
            null
        }
    }

    const val OPACITY_40_PERCENT = 102
    const val OPACITY_90_PERCENT = 230

    fun colorWithOpacity(opacity: Int, color: Int): Int {
        return Color.argb(
            opacity,
            Color.red(color),
            Color.green(color),
            Color.blue(color)
        )
    }
}
