package org.goodkarmaprojects.core.uifeedback.di

import org.goodkarmaprojects.core.uifeedback.data.MessageContainerView
import org.goodkarmaprojects.core.uifeedback.manager.MessageManager
import org.goodkarmaprojects.core.uifeedback.manager.MessageManagerImpl
import dagger.Module
import dagger.Provides
import org.goodkarmaprojects.core.resourceprovider.ResourceProvider

@Module
class UIFeedbackModule {

    @Provides
    fun provideFeedbackManager(view: MessageContainerView, resourceProvider: ResourceProvider) : MessageManager =
        MessageManagerImpl(view, resourceProvider)

}