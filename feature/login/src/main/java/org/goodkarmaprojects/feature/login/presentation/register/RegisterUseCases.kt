package org.goodkarmaprojects.feature.login.presentation.register

import org.goodkarmaprojects.core.network.commons.NetworkResult
import org.goodkarmaprojects.feature.login.domain.entities.User


interface RegisterUseCases {

    /**
     * Register with email, password and name
     * @param email
     * @param password
     * @param name
     * @return [User]
     */
    suspend fun registerWithEmail(email: String, password: String, name: String): NetworkResult<User>

    /**
     * Check if both password match
     * @param password
     * @param rePassword
     * @return [Boolean]
     */
    fun passwordsMatch(password: String, rePassword: String) : Boolean

    /**
     * Check if user name is blank
     * @param name
     * @return [Boolean]
     */
    fun isNameBlank(name: String) : Boolean
}