package com.afi.minby.di

import androidx.multidex.MultiDexApplication

class MinByApplication : MultiDexApplication() {

    companion object {
        private lateinit var app: MinByApplication

        @JvmStatic
        val instance
            get() = app
    }

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }
}
