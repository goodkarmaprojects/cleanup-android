package org.goodkarmaprojects.feature.login.data.repository

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import org.goodkarmaprojects.core.network.commons.ErrorResult
import org.goodkarmaprojects.core.network.commons.NetworkResult
import org.goodkarmaprojects.feature.login.data.transformer.DataTransformer
import org.goodkarmaprojects.feature.login.domain.entities.User
import org.goodkarmaprojects.feature.login.domain.usecases.AuthRepository
import java.lang.Exception
import java.util.concurrent.CancellationException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val dataTransformer: DataTransformer
) : AuthRepository {

    override suspend fun loginWithEmail(email: String, password: String): NetworkResult<User> {
        return try {
            firebaseAuth.useAppLanguage()
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            NetworkResult.Success(dataTransformer.firebaseUserToUser(authResult.user)!!)
        }catch (e: CancellationException){
            NetworkResult.APIError
        }catch (e: Exception){
            NetworkResult.ServerError(500, ErrorResult(null, e.localizedMessage, null))
        }
    }

    override suspend fun registerWithEmail(email: String, password: String, name: String) : NetworkResult<User>{
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val profileUpdates = userProfileChangeRequest {
                displayName = name
                photoUri = Uri.parse("https://example.com/jane-q-user/profile.jpg") //TODO
            }
            authResult.user!!.updateProfile(profileUpdates).await()
            authResult.user!!.sendEmailVerification().await()
            NetworkResult.Success(dataTransformer.firebaseUserToUser(authResult.user)!!)
        }catch (e: CancellationException){
            NetworkResult.APIError
        }catch (e: Exception){
            Log.e("tag", "", e)
            NetworkResult.ServerError(500, ErrorResult(null, e.localizedMessage, null))
        }
    }

    override suspend fun resendEmailVerification() : NetworkResult<User>{
        return try {
            val user = firebaseAuth.currentUser
            user!!.sendEmailVerification().await()
            NetworkResult.Success(dataTransformer.firebaseUserToUser(user)!!)
        }catch (e: CancellationException){
            NetworkResult.APIError
        }catch (e: Exception){
            Log.e("tag", "", e)
            NetworkResult.ServerError(500, ErrorResult(null, e.localizedMessage, null))
        }
    }

    override suspend fun resetPassword(email: String): NetworkResult<Unit> {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            NetworkResult.Success(Unit)
        }catch (e: CancellationException){
            NetworkResult.APIError
        }catch (e: Exception){
            Log.e("tag", "", e)
            NetworkResult.ServerError(500, ErrorResult(null, e.localizedMessage, null))
        }
    }

    override fun getCurrentUser() = dataTransformer.firebaseUserToUser(firebaseAuth.currentUser)

}