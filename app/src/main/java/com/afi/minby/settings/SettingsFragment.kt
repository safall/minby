package com.afi.minby.settings

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.afi.minby.R
import com.afi.minby.core.getDimensionPixelSize
import com.afi.minby.home.homemenu.AdapterCallback
import com.afi.minby.settings.subpage.KEY_PRIVACY
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val settingsAdapter = SettingsAdapter(
            SettingsItemList.getItems(),
            object : AdapterCallback {
                override fun <T> onItemClicked(item: T) {
                    navigateTo((item as SettingsItem).type)
                }
            })

        with(recyclerView) {
            adapter = settingsAdapter
            addItemDecoration(SettingsItemDecoration(context.getDimensionPixelSize(R.dimen.settings_item_spacing)))
        }
    }

    private fun navigateTo(type: SettingsItemType) {
        when (type) {
            SettingsItemType.SETTINGS_ITEM_CHANGE_PASSWORD -> NavHostFragment.findNavController(
                hostFragment
            ).navigate(R.id.changePasswordDialog)
            SettingsItemType.SETTINGS_ITEM_PRIVACY -> NavHostFragment.findNavController(hostFragment)
                .navigate(R.id.privacyPolicyDialog)
            SettingsItemType.SETTINGS_ITEM_TNC -> {
                val bundle = Bundle().apply { arguments?.putBoolean(KEY_PRIVACY, false) }
                NavHostFragment.findNavController(hostFragment)
                    .navigate(R.id.privacyPolicyDialog, bundle)
            }
        }

    }
}
