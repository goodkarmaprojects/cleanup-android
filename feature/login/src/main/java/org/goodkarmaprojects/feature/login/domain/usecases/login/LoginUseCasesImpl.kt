package org.goodkarmaprojects.feature.login.domain.usecases.login

import org.goodkarmaprojects.core.network.commons.NetworkResult
import org.goodkarmaprojects.core.uifeedback.manager.MessageManager
import org.goodkarmaprojects.feature.login.R
import org.goodkarmaprojects.feature.login.domain.entities.User
import org.goodkarmaprojects.feature.login.domain.usecases.AuthRepository
import org.goodkarmaprojects.feature.login.domain.usecases.MessageMapper
import org.goodkarmaprojects.feature.login.presentation.login.LoginUseCases
import javax.inject.Inject

class LoginUseCasesImpl @Inject constructor(
    private val repository: AuthRepository,
    private val messageManager: MessageManager): LoginUseCases {

    override suspend fun loginWithEmail(email: String, password: String): NetworkResult<User> {
        val response = repository.loginWithEmail(email, password)
        if(response !is NetworkResult.Success) showLoginError(response)

        return response
    }

    override suspend fun resendVerifyLink() : NetworkResult<User> {
        val response = repository.resendEmailVerification()
        when (response) {
            is NetworkResult.Success -> showResendLinkSuccess()
            else -> showResendLinkError()
        }
        return response
    }

    override suspend fun sendForgottenPasswordEmail(email: String) : NetworkResult<Unit>{
        val response = repository.resetPassword(email)
        when (response) {
            is NetworkResult.Success -> showResetPasswordSuccess()
            else -> showLoginError(response)
        }

        return response
    }



    private fun showLoginError(response: NetworkResult<Any>) {
        if(response is NetworkResult.ServerError){
            val errorMessage = response.errorAPIBody?.message
            messageManager.showMessage(
                MessageMapper.getByMessage(errorMessage).messageResource)
        }
    }

    override fun getLoggedUser() = repository.getCurrentUser()

    override fun showNotVerifiedEmailError() = messageManager.showMessage(R.string.login_error_email_not_verified)

    private fun showResendLinkSuccess() = messageManager.showMessage(R.string.login_success_resend_link)

    private fun showResendLinkError() = messageManager.showMessage(R.string.login_error_resend_link)

    private fun showResetPasswordSuccess() = messageManager.showMessage(R.string.reset_password_success)
}