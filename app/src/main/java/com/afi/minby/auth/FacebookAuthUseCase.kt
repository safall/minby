package com.afi.minby.auth

import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FacebookAuthUseCase @Inject constructor(private val callbackManager: CallbackManager) {

    private val loginPermissions = listOf(
        "email"
    )

    fun authenticate(fragment: Fragment) {
        LoginManager.getInstance().logInWithReadPermissions(fragment, loginPermissions)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("zzz2", "callback")
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    fun setAuthenticationCallback(callback: FacebookCallback<LoginResult>) {
        LoginManager.getInstance().registerCallback(callbackManager, callback)
    }

    fun removeAuthenticationCallback() {
        LoginManager.getInstance().unregisterCallback(callbackManager)
    }

    fun logOut() {
        LoginManager.getInstance().logOut()
    }
}
