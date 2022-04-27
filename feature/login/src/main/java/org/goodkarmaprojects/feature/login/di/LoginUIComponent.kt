package org.goodkarmaprojects.feature.login.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import org.goodkarmaprojects.cleanup.di.ModuleDependencies
import org.goodkarmaprojects.core.resourceprovider.di.ResourceProviderModule
import org.goodkarmaprojects.core.uifeedback.data.MessageContainerView
import org.goodkarmaprojects.core.uifeedback.di.UIFeedbackModule
import org.goodkarmaprojects.feature.login.presentation.login.LoginFragment
import org.goodkarmaprojects.feature.login.presentation.login.dialog.ForgottenPasswordDialog
import org.goodkarmaprojects.feature.login.presentation.login.dialog.LoginVerifyEmailDialog
import org.goodkarmaprojects.feature.login.presentation.register.RegisterFragment
import org.goodkarmaprojects.feature.login.presentation.register.dialog.RegisterVerifyEmailDialog

@Component(
    dependencies = [ModuleDependencies::class],
    modules = [
        LoginModule::class,
        LoginUIModule::class,
        UIFeedbackModule::class,
        ResourceProviderModule::class]
)

interface LoginUIComponent {
    fun inject (fragment: LoginFragment)
    fun inject (fragment: RegisterFragment)
    fun inject (fragment: RegisterVerifyEmailDialog)
    fun inject (fragment: LoginVerifyEmailDialog)
    fun inject (fragment: ForgottenPasswordDialog)

    @Component.Builder
    interface Builder {
        fun messageContainer(@BindsInstance containerView: MessageContainerView): Builder
        fun application(@BindsInstance app: Application): Builder
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(daggerDependencies: ModuleDependencies): Builder
        fun build(): LoginUIComponent
    }
}
