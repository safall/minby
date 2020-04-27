package com.afi.minby.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.afi.minby.auth.login.LoginViewModel
import com.afi.minby.auth.signup.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindSignupViewModel(signUpViewModel: SignUpViewModel): ViewModel
}
