package com.afi.minby.auth.signup

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afi.minby.R
import com.afi.minby.auth.FacebookAuthUseCase
import com.afi.minby.auth.GoogleAuthUseCase
import com.afi.minby.repository.RemoteRepository
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val facebookAuthUseCase: FacebookAuthUseCase,
    private val googleAuthUseCase: GoogleAuthUseCase,
    private val repository: RemoteRepository

) : ViewModel() {
    val fbuseCaseLiveData: MutableLiveData<FacebookAuthUseCase> = MutableLiveData()
    val googleUseCaseLiveData: MutableLiveData<GoogleAuthUseCase> = MutableLiveData()
    val registerUser: MutableLiveData<Boolean> = MutableLiveData()
    val registrationFailed: MutableLiveData<String> = MutableLiveData()
    private val facebookObserver = FacebookObserver()
    private val disposable = CompositeDisposable()
    fun initFBRegister() {
        facebookAuthUseCase.logOut()
        facebookAuthUseCase.setAuthenticationCallback(facebookObserver)
        fbuseCaseLiveData.postValue(facebookAuthUseCase)
    }

    fun initGoogleRegister() {
        googleUseCaseLiveData.postValue(googleAuthUseCase)
    }

    fun attemptRegister(email: String, password: String, conformpassword: String) {

        if (password.isNullOrEmpty() != conformpassword.isNullOrEmpty()) {
            registrationFailed.postValue(R.string.Password_not_matched.toString())
            return
        }
        disposable.add(
                repository.signup(email, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                    .subscribe({
                        registerUser.postValue(true)
                    }, {
                        registerUser.postValue(true)
                    })
        )
    }

    private inner class FacebookObserver : FacebookCallback<LoginResult> {
        override fun onSuccess(result: LoginResult?) {
            val accessToken = result?.accessToken?.token
            Log.d("accessToken facebook", accessToken.toString())
            registerUser.postValue(true)
        }
        override fun onCancel() {
            Log.d("cancelled", "cancelled")
        }
        override fun onError(error: FacebookException?) {
            Log.d("Error", error?.message.toString())
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        facebookAuthUseCase.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 200) {
            checkForGoogleAuthResult(data)
        }
    }

    private fun checkForGoogleAuthResult(data: Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        checkForLogin(task, false)
    }

    fun checkForLogin(task: Task<GoogleSignInAccount>, isSilentLogin: Boolean) {
        try {
            val account = task.getResult(ApiException::class.java)
            val accessToken = account?.idToken
            Log.d("accessToken google", accessToken.toString())
            registerUser.postValue(true)
        } catch (e: ApiException) {
            if (isSilentLogin) {
                registrationFailed.postValue("Failed to register")
            } else {
                registerUser.postValue(true)
            }
            Log.w("google signin error", "signInResult:failed code=" + e.statusCode)
        }
    }

    override fun onCleared() {
        super.onCleared()
        facebookAuthUseCase.removeAuthenticationCallback()
        disposable.dispose()
    }
}
