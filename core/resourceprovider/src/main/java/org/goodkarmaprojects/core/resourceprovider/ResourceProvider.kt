package org.goodkarmaprojects.core.resourceprovider

interface ResourceProvider {

    /**
     * Provide the text related to a resource
     */
    fun provideText(resource: Int): String

}