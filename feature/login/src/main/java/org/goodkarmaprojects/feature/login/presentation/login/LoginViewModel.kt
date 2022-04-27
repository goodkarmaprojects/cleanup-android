package org.goodkarmaprojects.feature.login.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.launch
import org.goodkarmaprojects.core.network.commons.NetworkResult
import org.goodkarmaprojects.core.uicommons.data.UIState
import org.goodkarmaprojects.core.uicommons.ui.BaseViewModel
import org.goodkarmaprojects.feature.login.domain.entities.User
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val useCases: LoginUseCases,
    private val mainDispatcher: MainCoroutineDispatcher
) : BaseViewModel() {

    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val loginResult = MutableLiveData<User>()
    val forgottenPasswordEmail = MutableLiveData("")
    val forgottenPasswordSuccess = MutableLiveData(false)

    fun doLogin(){
        viewModelScope.launch(mainDispatcher) {
            uiState.postValue(UIState.LOADING)
            when(val loginResponse = useCases.loginWithEmail(email.value!!, password.value!!)){
                is NetworkResult.Success -> loginResult.postValue(loginResponse.data)
                is NetworkResult.ServerError -> {
                    loginResponse.errorAPIBody?.message
                }
                else -> {
                    //TODO login failure
                }
            }
            uiState.postValue(UIState.FINISHED)
        }
    }

    fun resendVerifyLink(){
        viewModelScope.launch(mainDispatcher) {
            useCases.resendVerifyLink()
        }
    }

    fun sendForgottenPasswordEmail() {
        viewModelScope.launch(mainDispatcher) {
            val result = useCases.sendForgottenPasswordEmail(forgottenPasswordEmail.value!!)
            if(result is NetworkResult.Success) forgottenPasswordSuccess.postValue(true)
        }
    }

    fun getCurrentUser(): User? =  useCases.getLoggedUser()

}
