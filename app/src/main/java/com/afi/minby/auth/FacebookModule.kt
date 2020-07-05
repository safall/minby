package com.afi.minby.auth

import com.facebook.CallbackManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object FacebookModule {

    @Provides
    @Singleton
    fun provideCallbackManager() = CallbackManager.Factory.create()
}
