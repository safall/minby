package com.afi.minby.di

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val applicationContext: Context) {

    @Provides fun provideApplicationContext(): Context = applicationContext

    @Provides
    @Singleton
    internal fun provideConnectivityManager(): ConnectivityManager {
        return applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}
