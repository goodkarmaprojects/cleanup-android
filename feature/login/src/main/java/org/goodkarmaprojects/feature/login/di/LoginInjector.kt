package org.goodkarmaprojects.feature.login.di

import android.R
import dagger.hilt.android.EntryPointAccessors
import org.goodkarmaprojects.cleanup.di.ModuleDependencies
import org.goodkarmaprojects.core.uifeedback.data.MessageContainerView
import org.goodkarmaprojects.feature.login.presentation.login.LoginFragment
import org.goodkarmaprojects.feature.login.presentation.login.dialog.ForgottenPasswordDialog
import org.goodkarmaprojects.feature.login.presentation.login.dialog.LoginVerifyEmailDialog
import org.goodkarmaprojects.feature.login.presentation.register.RegisterFragment
import org.goodkarmaprojects.feature.login.presentation.register.dialog.RegisterVerifyEmailDialog

internal fun LoginFragment.inject() {
    DaggerLoginUIComponent.builder()
        .context(requireContext())
        .application(requireActivity().application)
        .messageContainer(MessageContainerView(activity?.findViewById(R.id.content)!!))
        .appDependencies(
            EntryPointAccessors.fromApplication(
                requireContext(),
                ModuleDependencies::class.java
            )
        )
        .build()
        .inject(this)
}

internal fun RegisterFragment.inject() {
    DaggerLoginUIComponent.builder()
        .context(requireContext())
        .application(requireActivity().application)
        .messageContainer(MessageContainerView(activity?.findViewById(R.id.content)!!))
        .appDependencies(
            EntryPointAccessors.fromApplication(
                requireContext(),
                ModuleDependencies::class.java
            )
        )
        .build()
        .inject(this)
}

internal fun RegisterVerifyEmailDialog.inject() {
    DaggerLoginUIComponent.builder()
        .context(requireContext())
        .application(requireActivity().application)
        .messageContainer(MessageContainerView(activity?.findViewById(R.id.content)!!))
        .appDependencies(
            EntryPointAccessors.fromApplication(
                requireContext(),
                ModuleDependencies::class.java
            )
        )
        .build()
        .inject(this)
}

internal fun LoginVerifyEmailDialog.inject() {
    DaggerLoginUIComponent.builder()
        .context(requireContext())
        .application(requireActivity().application)
        .messageContainer(MessageContainerView(activity?.findViewById(R.id.content)!!))
        .appDependencies(
            EntryPointAccessors.fromApplication(
                requireContext(),
                ModuleDependencies::class.java
            )
        )
        .build()
        .inject(this)
}

internal fun ForgottenPasswordDialog.inject() {
    DaggerLoginUIComponent.builder()
        .context(requireContext())
        .application(requireActivity().application)
        .messageContainer(MessageContainerView(activity?.findViewById(R.id.content)!!))
        .appDependencies(
            EntryPointAccessors.fromApplication(
                requireContext(),
                ModuleDependencies::class.java
            )
        )
        .build()
        .inject(this)
}
