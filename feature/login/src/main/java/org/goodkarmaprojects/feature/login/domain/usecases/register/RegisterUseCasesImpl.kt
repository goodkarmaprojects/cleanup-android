package org.goodkarmaprojects.feature.login.domain.usecases.register

import org.goodkarmaprojects.core.network.commons.NetworkResult
import org.goodkarmaprojects.core.uifeedback.manager.MessageManager
import org.goodkarmaprojects.feature.login.R
import org.goodkarmaprojects.feature.login.domain.entities.User
import org.goodkarmaprojects.feature.login.domain.usecases.AuthRepository
import org.goodkarmaprojects.feature.login.domain.usecases.MessageMapper
import org.goodkarmaprojects.feature.login.presentation.register.RegisterUseCases
import javax.inject.Inject

class RegisterUseCasesImpl @Inject constructor(
    private val repository: AuthRepository,
    private val messageManager: MessageManager): RegisterUseCases {

    override suspend fun registerWithEmail(email: String, password: String, name: String): NetworkResult<User> {
        val response = repository.registerWithEmail(email, password, name)
        if(response !is NetworkResult.Success) showRegisterError(response)

        return response
    }

    override fun passwordsMatch(password: String, rePassword: String): Boolean {
        val result = password == rePassword
        if(result.not()) showNotMatchingPasswordError()
        return result
    }

    override fun isNameBlank(name: String): Boolean {
        val result = name.isBlank()
        if(result) showNameBlankError()
        return result
    }

    private fun showRegisterError(response: NetworkResult<User>) {
        if(response is NetworkResult.ServerError){
            val errorMessage = response.errorAPIBody?.message
            messageManager.showMessage(
                MessageMapper.getByMessage(errorMessage).messageResource)
        }
    }

    private fun showNameBlankError() = messageManager.showMessage(R.string.register_error_password_blank_name)

    private fun showNotMatchingPasswordError() = messageManager.showMessage(R.string.register_error_password_not_match)

}