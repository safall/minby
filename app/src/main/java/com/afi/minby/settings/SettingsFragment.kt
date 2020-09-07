package com.afi.minby.settings

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.afi.minby.R
import com.afi.minby.core.getDimensionPixelSize
import com.afi.minby.home.homemenu.AdapterCallback
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val settingsAdapter = SettingsAdapter(
            SettingsItemList.getItems(),
            object : AdapterCallback {
                override fun <T> onItemClicked(item: T) {
                    when ((item as SettingsItem).type) {
                        SettingsItemType.SETTINGS_ITEM_CHANGE_PASSWORD -> navigateTo(R.id.changePasswordDialog)
                    }
                }
            })

        with(recyclerView) {
            adapter = settingsAdapter
            addItemDecoration(SettingsItemDecoration(context.getDimensionPixelSize(R.dimen.settings_item_spacing)))
        }
    }

    private fun navigateTo(@IdRes resId: Int) {
        NavHostFragment.findNavController(hostFragment).navigate(resId)
    }
}
