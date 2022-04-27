package org.goodkarmaprojects.core.resourceprovider.impl

import android.app.Application
import org.goodkarmaprojects.core.resourceprovider.ResourceProvider
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(private val application: Application):
    ResourceProvider {

    override fun provideText(resource: Int): String = application.getString(resource)
}