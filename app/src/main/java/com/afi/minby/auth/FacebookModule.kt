package com.afi.minby.auth

import com.facebook.CallbackManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class FacebookModule {

    @Provides
    @Singleton
    fun provideCallbackManager() = CallbackManager.Factory.create()
}
