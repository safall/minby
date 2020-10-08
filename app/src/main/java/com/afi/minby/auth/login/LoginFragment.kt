package com.afi.minby.auth.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.afi.minby.R
import com.afi.minby.auth.ScreenState
import com.afi.minby.core.isValidEmail
import com.afi.minby.home.settings.subpage.KEY_PRIVACY
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_launcher.*
import kotlinx.android.synthetic.main.fragment_login.*


@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var client: GoogleSignInClient

    private var screenState: ScreenState = ScreenState.LOGIN

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        facebook.setOnClickListener {
            NavHostFragment.findNavController(host_fragment).navigate(R.id.loginToHomeActivity)
            activity?.finish()
        }

        google.setOnClickListener {
            NavHostFragment.findNavController(host_fragment).navigate(R.id.loginToHomeActivity)
            activity?.finish()
        }

        button.setOnClickListener {
            viewModel.attemptLogin(email.text.toString(), password.text.toString())
        }

        register.setOnClickListener {
            when (screenState) {
                ScreenState.LOGIN -> setRegisterState()
                ScreenState.SIGNUP -> setLoginState()
            }
        }

        email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (!email.text.isValidEmail()) {
                    email.error = getString(R.string.invalid_email_format)
                } else {
                    email.error = null
                }
                button.isEnabled = s.isNotBlank() && password.text.isNotBlank() && email.text.isValidEmail()
            }
        })

        password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = run {
                if (screenState == ScreenState.SIGNUP) {
                    val pw = password.text.toString()
                    val cPassword = confirmPassword.text.toString()
                    val isPasswordValid = pw == cPassword
                    if (!isPasswordValid) {
                        confirmPassword.error = getString(R.string.password_donot_match)
                        password.error = getString(R.string.password_donot_match)
                    } else if (!email.text.isValidEmail()) {
                        email.error = getString(R.string.invalid_email_format)
                    } else {
                        email.error = null
                        confirmPassword.error = null
                        password.error = null
                    }
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
                if (screenState == ScreenState.LOGIN) {
                    button.isEnabled = s.isNotBlank() && email.text.isNotBlank() && email.text.isValidEmail()
                } else {
                    button.isEnabled =
                        s.isNotBlank() && email.text.isNotBlank() && password.text.isNotBlank() && email.text.isValidEmail()
                }
            }
        })

        password.setOnEditorActionListener { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    if (button.isEnabled) {
                        viewModel.attemptLogin(email.text.toString(), password.text.toString())
                    }
                    true
                }
                else -> false
            }
        }

        confirmPassword.setOnEditorActionListener { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    if (button.isEnabled) {
                        viewModel.attemptLogin(email.text.toString(), password.text.toString())
                    }
                    true
                }
                else -> false
            }
        }

        confirmPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = run {
                if (screenState == ScreenState.SIGNUP) {
                    val pw = password.text.toString()
                    val cPassword = confirmPassword.text.toString()
                    val isPasswordValid = pw == cPassword
                    if (!isPasswordValid) {
                        confirmPassword.error = getString(R.string.password_donot_match)
                        password.error = getString(R.string.password_donot_match)
                    } else {
                        confirmPassword.error = null
                        password.error = null
                    }
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
                button.isEnabled = s.isNotBlank() && email.text.isNotBlank() && password.text.isNotBlank() && email.text.isValidEmail()
            }
        })

        privacy.setOnClickListener {
            NavHostFragment.findNavController(host_fragment)
                .navigate(R.id.privacyPolicyDialog)
        }

        terms.setOnClickListener {
            val bundle = Bundle().apply { arguments?.putBoolean(KEY_PRIVACY, false) }
            NavHostFragment.findNavController(host_fragment)
                .navigate(R.id.privacyPolicyDialog, bundle)
        }
    }

    private fun setRegisterState() {
        screenState = ScreenState.SIGNUP
        val animZoomIn = AnimationUtils.loadAnimation(context, R.anim.zoom_in)
        circularView.startAnimation(animZoomIn)
        confirmPassword.visibility = View.VISIBLE
        button.text = requireContext().getText(R.string.signup)
        dontHaveUser.text = requireContext().getText(R.string.already_registered)
        register.text = requireContext().getText(R.string.login)
        containerHeading.text = requireContext().getText(R.string.signup)
        button.isEnabled = false
        password.imeOptions = EditorInfo.IME_ACTION_NEXT
    }

    private fun setLoginState() {
        screenState = ScreenState.LOGIN
        val animZoomOut = AnimationUtils.loadAnimation(context, R.anim.zoom_out)
        circularView.startAnimation(animZoomOut)
        confirmPassword.visibility = View.GONE
        button.text = requireContext().getText(R.string.login)
        dontHaveUser.text = requireContext().getText(R.string.dontHaveUser)
        register.text = requireContext().getText(R.string.signup)
        containerHeading.text = requireContext().getText(R.string.login)
        confirmPassword.text.clear()
        button.isEnabled = email.text.isNotBlank() && password.text.isNotBlank()
        password.imeOptions = EditorInfo.IME_ACTION_DONE
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.fbuseCaseLiveData.observe(viewLifecycleOwner, Observer {
            it.authenticate(this)
        })

        viewModel.googleUseCaseLiveData.observe(viewLifecycleOwner, Observer {
            client = it.getGoogleSignInClient(requireActivity())
            client.silentSignIn().addOnCompleteListener {
                viewModel.checkForLogin(it, true)
            }
        })

        viewModel.authenticationSuccessful.observe(viewLifecycleOwner, Observer {
            if (it) {
                NavHostFragment.findNavController(host_fragment).navigate(R.id.loginToHomeActivity)
                activity?.finish()
            } else {
                Toast.makeText(requireContext(), "Problem signing in to google", Toast.LENGTH_LONG)
                    .show()
            }
        })

        viewModel.silentAuthenticationFailed.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                startActivityForResult(client.signInIntent, 200)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.onActivityResult(requestCode, resultCode, data)
    }
}
