package com.afi.minby.di

import com.afi.minby.auth.FacebookModule
import com.afi.minby.auth.login.LoginFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, FacebookModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(loginFragment: LoginFragment)
}
