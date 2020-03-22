package com.afi.minby.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.afi.minby.R
import com.afi.minby.di.MinByApplication
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_launcher.*
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MinByApplication.instance.component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
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
            viewModel.attemptLogin(mailId.text.toString(), passwordEditText.text.toString())
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
            val client = it.getGoogleSignInClient(requireActivity())
            val signInIntent: Intent = client.signInIntent
            startActivityForResult(signInIntent, 200)
        })

        viewModel.authenticationSuccessful.observe(this, Observer {
            NavHostFragment.findNavController(host_fragment).navigate(R.id.loginToHomeActivity)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.onActivityResult(requestCode, resultCode, data)
    }
}
