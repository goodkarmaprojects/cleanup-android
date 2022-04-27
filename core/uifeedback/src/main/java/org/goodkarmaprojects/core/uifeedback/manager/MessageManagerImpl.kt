package org.goodkarmaprojects.core.uifeedback.manager

import com.google.android.material.snackbar.Snackbar
import org.goodkarmaprojects.core.resourceprovider.ResourceProvider
import org.goodkarmaprojects.core.uifeedback.data.FeedbackType
import org.goodkarmaprojects.core.uifeedback.data.MessageContainerView
import org.goodkarmaprojects.core.uifeedback.data.MessageLevel

class MessageManagerImpl(private val cView: MessageContainerView,
                         private val resourceProvider: ResourceProvider
): MessageManager {

    override fun showMessage(message: String, type: FeedbackType, level: MessageLevel) {
       when(type){
           FeedbackType.SNACKBAR -> printSnackBar(message)
       }
    }

    override fun showMessage(resource: Int, type: FeedbackType, level: MessageLevel) {
        when(type){
            FeedbackType.SNACKBAR -> printSnackBar(resourceProvider.provideText(resource))
        }
    }

    private fun printSnackBar(message: String){
        Snackbar.make(cView.view, message, Snackbar.LENGTH_LONG).show()
    }
}