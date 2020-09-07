package com.afi.minby.settings

import androidx.annotation.StringRes

data class SettingsItem(
    @StringRes val title: Int,
    @StringRes val textRight: Int?,
    val isEnabled: Boolean?,
    val type: SettingsItemType
)
