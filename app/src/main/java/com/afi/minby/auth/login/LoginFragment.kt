package com.afi.minby.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.afi.minby.R
import com.afi.minby.di.MinByApplication
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.android.synthetic.main.activity_launcher.*
import kotlinx.android.synthetic.main.login_fragment.*
import javax.inject.Inject

class LoginFragment : Fragment(R.layout.login_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: LoginViewModel

    private lateinit var client: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MinByApplication.instance.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        facebookButton.setOnClickListener {
            viewModel.initFBLogin()
        }

        googleButton.setOnClickListener {
            viewModel.initGoogleLogin()
        }

        loginButton.setOnClickListener {
            viewModel.attemptLogin(emailEditText.text.toString(), passwordEditText.text.toString())
        }

        registerButton.setOnClickListener {
            NavHostFragment.findNavController(host_fragment)
                .navigate(R.id.loginTosignUpFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)

        viewModel.fbuseCaseLiveData.observe(this, Observer {
            it.authenticate(this)
        })

        viewModel.googleUseCaseLiveData.observe(this, Observer {
            client = it.getGoogleSignInClient(requireActivity())
            client.silentSignIn().addOnCompleteListener {
                viewModel.checkForLogin(it, true)
            }
        })

        viewModel.authenticationSuccessful.observe(this, Observer {
            if (it) {
                NavHostFragment.findNavController(host_fragment).navigate(R.id.loginToHomeActivity)
            } else {
                Toast.makeText(requireContext(), "Problem signing in to google", Toast.LENGTH_LONG)
                    .show()
            }
        })

        viewModel.silentAuthenticationFailed.observe(this, Observer {
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
