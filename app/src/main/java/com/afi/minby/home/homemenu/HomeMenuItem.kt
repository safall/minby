package com.afi.minby.home.homemenu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class HomeMenuItem(@StringRes val title: Int, @StringRes val desc: Int, @DrawableRes val iconID: Int)
