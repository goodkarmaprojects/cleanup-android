package org.goodkarmaprojects.feature.login.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.goodkarmaprojects.cleanup.di.ViewModelFactory
import org.goodkarmaprojects.cleanup.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.goodkarmaprojects.feature.login.presentation.login.LoginViewModel
import org.goodkarmaprojects.feature.login.presentation.register.RegisterViewModel

@Module
abstract class LoginUIModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun loginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    internal abstract fun registerViewModel(viewModel: RegisterViewModel): ViewModel
}