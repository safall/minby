package com.afi.minby.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.afi.minby.R
import com.afi.minby.core.getDimensionPixelSize
import com.afi.minby.home.homemenu.AdapterCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val settingsAdapter = SettingsAdapter(
            SettingsItemList.getItems(),
            object : AdapterCallback {
                override fun <T> onItemClicked(item: T) {
//                    NavHostFragment.findNavController(host_fragment)
//                        .navigate(R.id.categoriesFragmentToSelectLocationFragment)
                }
            })

        with(recyclerView) {
            adapter = settingsAdapter
            addItemDecoration(SettingsItemDecoration(context.getDimensionPixelSize(R.dimen.settings_item_spacing)))
        }
    }
}
