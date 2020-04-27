package com.afi.minby.auth.signup

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
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_launcher.*
import kotlinx.android.synthetic.main.login_fragment.registerButton
import kotlinx.android.synthetic.main.sign_up_fragment.*

class SignUpFragment : Fragment(R.layout.sign_up_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SignUpViewModel
    private lateinit var client: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MinByApplication.instance.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerButton.setOnClickListener {
            viewModel.attemptRegister(
                emailSignup.text.toString(),
                passwordSignup.text.toString(),
                rePasswordSignup.text.toString()
            )
        }

        facebookSignup.setOnClickListener {
            viewModel.initFBRegister()
        }

        googleSignup.setOnClickListener {
            viewModel.initGoogleRegister()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SignUpViewModel::class.java)
        viewModel.fbuseCaseLiveData.observe(this, Observer {
            it.authenticate(this)
        })

        viewModel.googleUseCaseLiveData.observe(this, Observer {
            client = it.getGoogleSignInClient(requireActivity())
            client.silentSignIn().addOnCompleteListener {
                viewModel.checkForLogin(it, true)
            }
        })

        viewModel.registrationFailed.observe(this, Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        viewModel.registerUser.observe(this, Observer {
            if (it) {
                NavHostFragment.findNavController(host_fragment).navigate(R.id.signupToHomeActivity)
            } else {
                Toast.makeText(requireContext(), "Fill all the required field", Toast.LENGTH_LONG)
                    .show()
            }
        })

        viewModel.registrationFailed.observe(this, Observer {
            if (it == "Failed to register") {
                startActivityForResult(client.signInIntent, 200)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.onActivityResult(requestCode, resultCode, data)
    }
}
