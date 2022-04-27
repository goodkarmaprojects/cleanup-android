package org.goodkarmaprojects.core.resourceprovider.di

import android.app.Application
import org.goodkarmaprojects.core.resourceprovider.ResourceProvider
import org.goodkarmaprojects.core.resourceprovider.impl.ResourceProviderImpl
import dagger.Module
import dagger.Provides

@Module
class ResourceProviderModule {

    @Provides
    fun resourceProviderProvider(app: Application): ResourceProvider = ResourceProviderImpl(app)
}