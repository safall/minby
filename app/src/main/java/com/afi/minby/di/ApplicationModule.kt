package com.afi.minby.di

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context) {

    @Provides
    @Singleton
    internal fun provideContext() = context

    @Provides
    @Singleton
    internal fun provideConnectivityManager(): ConnectivityManager {
        return context.applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}