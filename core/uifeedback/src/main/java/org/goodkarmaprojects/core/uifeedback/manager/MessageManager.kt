package org.goodkarmaprojects.core.uifeedback.manager

import org.goodkarmaprojects.core.uifeedback.data.FeedbackType
import org.goodkarmaprojects.core.uifeedback.data.MessageLevel

interface MessageManager {

    /**
     * Show message to the user
     */
    fun showMessage(message: String, type: FeedbackType = FeedbackType.SNACKBAR, level: MessageLevel = MessageLevel.INFO)

    /**
     * Show message to the user given a resource ID
     */
    fun showMessage(resource: Int, type: FeedbackType = FeedbackType.SNACKBAR, level: MessageLevel = MessageLevel.INFO)
}