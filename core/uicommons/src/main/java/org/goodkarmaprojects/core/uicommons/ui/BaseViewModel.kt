package org.goodkarmaprojects.core.uicommons.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.goodkarmaprojects.core.uicommons.data.UIState

abstract class BaseViewModel: ViewModel() {
    val uiState = MutableLiveData(UIState.FINISHED)

    fun onViewDestroyed(){
        uiState.value = UIState.FINISHED
    }

}