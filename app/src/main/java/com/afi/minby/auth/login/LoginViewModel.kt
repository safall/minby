package com.afi.minby.auth.login

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afi.minby.auth.FacebookAuthUseCase
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val facebookAuthUseCase: FacebookAuthUseCase) :
    ViewModel() {

        private val facebookObserver = FacebookObserver()
    val useCaseLiveData: MutableLiveData<FacebookAuthUseCase> = MutableLiveData()

    fun initFBLogin() {
        facebookAuthUseCase.logOut()
        facebookAuthUseCase.setAuthenticationCallback(facebookObserver)
        useCaseLiveData.postValue(facebookAuthUseCase)
    }

    private inner class FacebookObserver : FacebookCallback<LoginResult> {

        override fun onSuccess(result: LoginResult?) {
            val accessToken = result?.accessToken?.token
            Log.d("accessToken facebook", accessToken.toString())
        }

        override fun onCancel() {
            Log.d("cancelled", "cancelled")
        }

        override fun onError(error: FacebookException?) {
            Log.d("Error", error?.message.toString())
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("zzz1", "callback")
        facebookAuthUseCase.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCleared() {
        super.onCleared()
        facebookAuthUseCase.removeAuthenticationCallback()
    }
}
