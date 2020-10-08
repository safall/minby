package com.afi.minby.home.settings

import androidx.annotation.StringRes

data class SettingsItem(
    @StringRes val title: Int,
    @StringRes val textRight: Int?,
    val isEnabled: Boolean?,
    val type: SettingsItemType
)
