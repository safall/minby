package com.afi.minby.auth

import android.app.Activity
import com.afi.minby.R
import com.afi.minby.di.MinByApplication
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GoogleAuthUseCase @Inject constructor() {

    val googleSignInOptions by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(MinByApplication.instance.getString(R.string.server_client_id))
            .requestEmail()
            .build()
    }

    fun getGoogleSignInClient(activity: Activity): GoogleSignInClient {
        return GoogleSignIn.getClient(activity, googleSignInOptions)
    }

    fun logOut(activity: Activity) {
        GoogleSignIn.getClient(activity, googleSignInOptions).signOut()
    }
}
