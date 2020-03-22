package com.afi.minby.auth.login

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afi.minby.auth.FacebookAuthUseCase
import com.afi.minby.auth.GoogleAuthUseCase
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val facebookAuthUseCase: FacebookAuthUseCase,
    private val googleAuthUseCase: GoogleAuthUseCase
) :
    ViewModel() {

    private val facebookObserver = FacebookObserver()
    val fbuseCaseLiveData: MutableLiveData<FacebookAuthUseCase> = MutableLiveData()
    val googleUseCaseLiveData: MutableLiveData<GoogleAuthUseCase> = MutableLiveData()
    val authenticationSuccessful: MutableLiveData<Boolean> = MutableLiveData()
    val silentAuthenticationFailed: MutableLiveData<Boolean> = MutableLiveData()

    fun initFBLogin() {
        facebookAuthUseCase.logOut()
        facebookAuthUseCase.setAuthenticationCallback(facebookObserver)
        fbuseCaseLiveData.postValue(facebookAuthUseCase)
    }

    fun initGoogleLogin() {
        googleUseCaseLiveData.postValue(googleAuthUseCase)
    }

    fun attemptLogin(email: String, password: String) {
        authenticationSuccessful.postValue(true)
    }

    private inner class FacebookObserver : FacebookCallback<LoginResult> {

        override fun onSuccess(result: LoginResult?) {
            val accessToken = result?.accessToken?.token
            Log.d("accessToken facebook", accessToken.toString())
            authenticationSuccessful.postValue(true)
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
            authenticationSuccessful.postValue(true)
        } catch (e: ApiException) {
            if (isSilentLogin) {
                silentAuthenticationFailed.postValue(true)
            } else {
                authenticationSuccessful.postValue(false)
            }
            Log.w("google signin error", "signInResult:failed code=" + e.statusCode)
        }
    }

    override fun onCleared() {
        super.onCleared()
        facebookAuthUseCase.removeAuthenticationCallback()
    }
}
