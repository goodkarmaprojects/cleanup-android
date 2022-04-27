package org.goodkarmaprojects.feature.login.presentation.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.launch
import org.goodkarmaprojects.core.network.commons.NetworkResult
import org.goodkarmaprojects.core.uicommons.data.UIState
import org.goodkarmaprojects.core.uicommons.ui.BaseViewModel
import org.goodkarmaprojects.feature.login.domain.entities.User
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val useCases: RegisterUseCases,
    private val mainDispatcher: MainCoroutineDispatcher
) : BaseViewModel() {

    val name = MutableLiveData("")
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val rePassword = MutableLiveData("")
    val registerResult = MutableLiveData<User>()
    val dismissedVerifyDialog = MutableLiveData(false)
    val termsConditionsChecked = MutableLiveData(false)

    fun doRegister(){
        if(useCases.isNameBlank(name.value!!)) return
        if(useCases.passwordsMatch(password.value!!, rePassword.value!!).not()) return

        viewModelScope.launch(mainDispatcher) {
            uiState.postValue(UIState.LOADING)
            when(val loginResponse = useCases.registerWithEmail(email.value!!, password.value!!, name.value!!)){
                is NetworkResult.Success -> registerResult.postValue(loginResponse.data)
                is NetworkResult.ServerError -> loginResponse.errorAPIBody?.message
                else -> {
                    //TODO login failure
                }
            }
            uiState.postValue(UIState.FINISHED)
        }
    }
}
