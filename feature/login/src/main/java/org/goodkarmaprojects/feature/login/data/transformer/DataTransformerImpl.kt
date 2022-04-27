package org.goodkarmaprojects.feature.login.data.transformer

import com.google.firebase.auth.FirebaseUser
import org.goodkarmaprojects.feature.login.domain.entities.User

class DataTransformerImpl: DataTransformer {

    override fun firebaseUserToUser(firebaseUser: FirebaseUser?): User? {
        return when(firebaseUser == null){
            true -> null
            false -> User(
                firebaseUser.uid,
                firebaseUser.displayName,
                firebaseUser.email,
                firebaseUser.photoUrl.toString(),
                firebaseUser.isEmailVerified)
        }
    }
}