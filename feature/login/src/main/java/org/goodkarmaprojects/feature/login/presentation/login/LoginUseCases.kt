package org.goodkarmaprojects.feature.login.presentation.login

import org.goodkarmaprojects.core.network.commons.NetworkResult
import org.goodkarmaprojects.feature.login.domain.entities.User

interface LoginUseCases {

    /**
     * Login with email and password
     * @param email
     * @param password
     * @return [User]
     */
    suspend fun loginWithEmail(email: String, password: String): NetworkResult<User>

    /**
     * Check if user is already logged in
     * @return logged user, null if not
     */
    fun getLoggedUser() : User?

    /**
     * Display error to warn email is not verified
     */
    fun showNotVerifiedEmailError()

    /**
     * Resend the link to verify account
     */
    suspend fun resendVerifyLink(): NetworkResult<User>

    /**
     * Send email to reset password
     * @param email
     */
    suspend fun sendForgottenPasswordEmail(email: String) : NetworkResult<Unit>

}