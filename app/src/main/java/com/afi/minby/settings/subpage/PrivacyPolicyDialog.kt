package com.afi.minby.settings.subpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.afi.minby.R
import com.afi.minby.core.getDimensionPixelSize
import com.afi.minby.settings.SettingsItemDecoration
import com.afi.minby.settings.subpage.model.PrivacyList
import kotlinx.android.synthetic.main.dialog_change_password.backIcon
import kotlinx.android.synthetic.main.fragment_settings.*

class PrivacyPolicyDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullscreenDialogTheme_Settings)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_privacy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backIcon.setOnClickListener { dismiss() }
        with(recyclerView) {
            adapter = PrivacyAdapter(PrivacyList.getItems())
            addItemDecoration(SettingsItemDecoration(context.getDimensionPixelSize(R.dimen.settings_item_spacing)))
        }
    }
}
