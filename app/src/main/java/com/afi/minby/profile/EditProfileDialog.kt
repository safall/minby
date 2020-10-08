package com.afi.minby.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.afi.minby.R
import com.afi.minby.core.isValidEmail
import kotlinx.android.synthetic.main.dialog_edit_profile.*
import kotlinx.android.synthetic.main.dialog_edit_profile.email
import kotlinx.android.synthetic.main.fragment_login.*

class EditProfileDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullscreenDialogTheme_Settings)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backIcon.setOnClickListener { dismiss() }
        userAddress.setOnEditorActionListener { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.profile_updated),
                        Toast.LENGTH_LONG
                    ).show()
                    dismiss()
                    true
                }
                else -> false
            }
        }
        email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?)  {
                if (!email.text.isValidEmail()) {
                    email.error = getString(R.string.invalid_email_format)
                    button.isEnabled = false
                } else {
                    email.error = null
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                button.isEnabled = s.isNotBlank() && password.text.isNotBlank()
            }
        })
    }
}
