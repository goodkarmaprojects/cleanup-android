package org.goodkarmaprojects.feature.login.domain.usecases

import org.goodkarmaprojects.core.network.commons.NetworkResult
import org.goodkarmaprojects.feature.login.domain.entities.User

interface AuthRepository {

    /**
     * Login with email and password
     * @param email
     * @param password
     * @return [User]
     */
    suspend fun loginWithEmail(email: String, password: String) : NetworkResult<User>

    /**
     * Register with email, password and name
     * @param email
     * @param password
     * @param name
     * @return [User]
     */
    suspend fun registerWithEmail(email: String, password: String, name: String) : NetworkResult<User>

    /**
     * Resend email with link to verify account
     * @return [User]
     */
    suspend fun resendEmailVerification(): NetworkResult<User>

    /**
     * Get current logged user
     * @return [User]
     */
    fun getCurrentUser(): User?

    /**
     * Send email to reset password
     * @param email
     */
    suspend fun resetPassword(email: String): NetworkResult<Unit>
}