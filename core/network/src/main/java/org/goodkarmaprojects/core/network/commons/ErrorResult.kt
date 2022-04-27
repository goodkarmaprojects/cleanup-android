package org.goodkarmaprojects.core.network.commons

import kotlinx.serialization.SerialName

data class ErrorResult(
    @SerialName("errors")
    val errors: List<Any>?,
    @SerialName("message")
    val message: String?,
    @SerialName("status")
    val status: String?
)