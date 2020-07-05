package com.afi.minby.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.afi.minby.R
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_launcher.*
import kotlinx.android.synthetic.main.fragment_login.*

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var client: GoogleSignInClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        facebook.setOnClickListener {
            viewModel.initFBLogin()
        }

        google.setOnClickListener {
            viewModel.initGoogleLogin()
        }

        loginButton.setOnClickListener {
            viewModel.attemptLogin(password.text.toString(), password.text.toString())
        }

        register.setOnClickListener {
            val animZoomIn = AnimationUtils.loadAnimation(context, R.anim.zoom_in)
            circularView.startAnimation(animZoomIn)
            confirmPassword.visibility = View.VISIBLE
            backIcon.visibility = View.VISIBLE
        }

        backIcon.setOnClickListener {
            val animZoomOut = AnimationUtils.loadAnimation(context, R.anim.zoom_out)
            circularView.startAnimation(animZoomOut)
            backIcon.visibility = View.GONE
            confirmPassword.visibility = View.GONE
        }
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
