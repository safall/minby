package com.afi.minby.core

import android.app.Activity
import android.app.UiModeManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.annotation.IntegerRes
import com.afi.minby.R

private const val SCREEN_WIDTH_DP_THRESHOLD_TABLET = 600

val Context.isLandscape: Boolean
    get() = screenOrientation == Configuration.ORIENTATION_LANDSCAPE

val Context.isPortrait: Boolean
    get() = screenOrientation == Configuration.ORIENTATION_PORTRAIT

val Context.isPhone: Boolean
    get() = !isTablet

val Context.isTablet: Boolean
    get() {
        val configuration = resources.configuration
        val screenLayoutMask = configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK
        return screenLayoutMask == Configuration.SCREENLAYOUT_SIZE_XLARGE ||
                configuration.smallestScreenWidthDp >= SCREEN_WIDTH_DP_THRESHOLD_TABLET
    }

val Context.isTv: Boolean
    get() = isUiModeType(Configuration.UI_MODE_TYPE_TELEVISION)

val Context.isAuto: Boolean
    get() = isUiModeType(Configuration.UI_MODE_TYPE_CAR)

private fun Context.isUiModeType(uiModeType: Int): Boolean {
    return (getSystemService(Context.UI_MODE_SERVICE) as UiModeManager).currentModeType == uiModeType
}

val Context.screenHeightPixels: Int
    get() = resources.displayMetrics.heightPixels

val Context.screenWidthPixels: Int
    get() = resources.displayMetrics.widthPixels

val Context.screenOrientation: Int
    get() = resources.configuration.orientation

fun Context.getAndroidDimensionPixelSize(name: String): Int {
    val resId = resources.getIdentifier(name, "dimen", "android")
    return if (resId > 0) getDimensionPixelSize(resId) else 0
}

fun Context.getDimensionPixelSize(@DimenRes id: Int) = resources.getDimensionPixelSize(id)

fun Context.getPxFromDp(dp: Float): Int {
    return TypedValue
        .applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
        .toInt()
}

fun Context.getInt(@IntegerRes integerRes: Int): Int {
    return resources.getInteger(integerRes)
}

fun Context.getResourceEntryName(resourceId: Int): String {
    return resources.getResourceEntryName(resourceId)
}

fun Context.copyToClipboard(text: String) {
    with(getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager) {
        // Suppressed because Kotlin compiler returns error if we use property access ¯\_(ツ)_/¯
        @Suppress("UsePropertyAccessSyntax")
        setPrimaryClip(ClipData.newPlainText(StringUtils.getString(R.string.copied, this@copyToClipboard), text))
    }
}

fun Context.inflate(resId: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false): View {
    return LayoutInflater
        .from(this)
        .inflate(resId, parent, attachToRoot)
}

inline fun <reified T : Activity> Context.getActivity(): T? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) {
            return context as T
        }
        context = context.baseContext
    }
    return null
}
