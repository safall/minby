package com.afi.minby.auth.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.afi.minby.R
import com.afi.minby.auth.ScreenState
import com.afi.minby.settings.subpage.KEY_PRIVACY
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
                button.isEnabled = s.isNotBlank() && password.text.isNotBlank()
            }
        })

        password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                button.isEnabled = s.isNotBlank() && email.text.isNotBlank()
            }
        })

        confirmPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                button.isEnabled =
                    s.isNotBlank() && email.text.isNotBlank() && password.text.isNotBlank()
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
        button.text = requireContext().getText(R.string.sign_up)
        dontHaveUser.text = requireContext().getText(R.string.already_registered)
        register.text = requireContext().getText(R.string.logg_in)
        containerHeading.text = requireContext().getText(R.string.sign_up)
        button.isEnabled = false
    }

    private fun setLoginState() {
        screenState = ScreenState.LOGIN
        val animZoomOut = AnimationUtils.loadAnimation(context, R.anim.zoom_out)
        circularView.startAnimation(animZoomOut)
        confirmPassword.visibility = View.GONE
        button.text = requireContext().getText(R.string.logg_in)
        dontHaveUser.text = requireContext().getText(R.string.dont_have_use)
        register.text = requireContext().getText(R.string.register)
        containerHeading.text = requireContext().getText(R.string.logg_in)
        confirmPassword.text.clear()
        button.isEnabled = email.text.isNotBlank() && password.text.isNotBlank()
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
