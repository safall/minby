package com.afi.minby.di

import androidx.multidex.MultiDexApplication

class MinByApplication : MultiDexApplication() {
    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationContext(applicationContext)
            .build()
    }
}