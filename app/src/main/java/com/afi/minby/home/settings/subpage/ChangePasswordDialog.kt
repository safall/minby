package com.afi.minby.home.settings.subpage

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
import kotlinx.android.synthetic.main.dialog_change_password.*
import kotlinx.android.synthetic.main.dialog_change_password.backIcon
import kotlinx.android.synthetic.main.dialog_change_password.confirmPassword
import kotlinx.android.synthetic.main.dialog_change_password.save
import kotlinx.android.synthetic.main.dialog_edit_profile.*
import kotlinx.android.synthetic.main.fragment_login.*

class ChangePasswordDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullscreenDialogTheme_Settings)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backIcon.setOnClickListener { dismiss() }
        save.setOnClickListener {
            Toast.makeText(
                requireContext(),
                getString(R.string.password_changed),
                Toast.LENGTH_LONG
            ).show()
            dismiss()
        }

        newPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = run {
                val newP = newPassword.text.toString()
                val confP = confirmPassword.text.toString()
                val isPasswordValid = newP == confP
                if (!isPasswordValid) {
                    newPassword.error = getString(R.string.password_donot_match)
                    confirmPassword.error = getString(R.string.password_donot_match)
                } else {
                    newPassword.error = null
                    confirmPassword.error = null
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
                save.isEnabled =
                    s.isNotBlank() && confirmPassword.text.isNotBlank() && currentPassword.text.isNotBlank()
            }
        })

        confirmPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = run {
                val newP = newPassword.text.toString()
                val confP = confirmPassword.text.toString()
                val isPasswordValid = newP == confP
                if (!isPasswordValid) {
                    newPassword.error = getString(R.string.password_donot_match)
                    confirmPassword.error = getString(R.string.password_donot_match)
                } else {
                    newPassword.error = null
                    confirmPassword.error = null
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
                save.isEnabled =
                    s.isNotBlank() && newPassword.text.isNotBlank() && currentPassword.text.isNotBlank()
            }
        })

        confirmPassword.setOnEditorActionListener { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    if (save.isEnabled) {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.password_changed),
                            Toast.LENGTH_LONG
                        ).show()
                        dismiss()
                    }

                    true
                }
                else -> false
            }
        }

        currentPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                save.isEnabled =
                    s.isNotBlank() && newPassword.text.isNotBlank() && confirmPassword.text.isNotBlank()
            }
        })
    }
}
