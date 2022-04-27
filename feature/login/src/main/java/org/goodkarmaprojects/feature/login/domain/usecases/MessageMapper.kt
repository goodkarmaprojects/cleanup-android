package org.goodkarmaprojects.feature.login.domain.usecases

import org.goodkarmaprojects.feature.login.R

enum class MessageMapper(val firebaseMessage: String, val messageResource: Int) {
    INVALID_MAIL("There is no user record corresponding to this identifier. The user may have been deleted", R.string.login_invalid_email_error),
    PASSWORD_LENGTH("Password should be at least 6 characters", R.string.register_error_password_length),
    INVALID_PASSWORD("password is invalid", R.string.login_invalid_password_error),
    DEVICE_BLOCKED("We have blocked all requests from this device", R.string.login_invalid_device_blocked),
    EMAIL_BAD_FORMAT("The email address is badly formatted", R.string.login_invalid_email_bad_format),
    MANDATORY_FIELDS("Given String is empty or null", R.string.login_invalid_mandatory),
    MAIL_ALREADY_EXISTS("The email address is already in use by another account", R.string.register_mail_already_exists),
    GENERIC("", R.string.login_error);

    companion object {
        fun getByMessage(message: String?): MessageMapper{
            if(message == null) return GENERIC
            val item = values().find { message.contains(it.firebaseMessage, ignoreCase = true) }
            return when (item) {
                null -> GENERIC
                else -> item
            }
        }
    }

}