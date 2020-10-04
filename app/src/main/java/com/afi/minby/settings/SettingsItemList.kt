package com.afi.minby.settings

import com.afi.minby.R
import com.afi.minby.settings.SettingsItemType.*

object SettingsItemList {

    fun getItems(): List<SettingsItem> {
        return listOf(
            SettingsItem(R.string.push_notification, null, true, SETTINGS_ITEM_PUSH_NOTIFICATION),
            SettingsItem(R.string.change_password, null, null, SETTINGS_ITEM_CHANGE_PASSWORD),
            SettingsItem(
                R.string.facebook,
                R.string.connected,
                false,
                SETTINGS_ITEM_FACEBOOK
            ),
            SettingsItem(
                R.string.google,
                R.string.not_connected,
                false,
                SETTINGS_ITEM_GOOGLE
            ),
            SettingsItem(R.string.terms_and_conditions, null, null, SETTINGS_ITEM_TNC),
            SettingsItem(R.string.privacy_policy, null, null, SETTINGS_ITEM_PRIVACY),
            SettingsItem(
                R.string.version,
                R.string.version_number,
                null,
                SETTINGS_ITEM_VERSION
            ),
            SettingsItem(R.string.logout, null, null, SETTINGS_ITEM_LOGOUT)
        )
    }
}
