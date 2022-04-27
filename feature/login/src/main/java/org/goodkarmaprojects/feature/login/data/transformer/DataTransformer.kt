package org.goodkarmaprojects.feature.login.data.transformer

import com.google.firebase.auth.FirebaseUser
import org.goodkarmaprojects.feature.login.domain.entities.User

interface DataTransformer {

    /**
     * Get the current user, null if it is not logged in
     * @param firebaseUser
     * @return [User]
     */
    fun firebaseUserToUser(firebaseUser: FirebaseUser?) : User?
}