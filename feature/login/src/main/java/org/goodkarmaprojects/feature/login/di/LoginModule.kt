package org.goodkarmaprojects.feature.login.di


import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import org.goodkarmaprojects.core.uifeedback.manager.MessageManager
import org.goodkarmaprojects.feature.login.data.repository.AuthRepositoryImpl
import org.goodkarmaprojects.feature.login.data.transformer.DataTransformer
import org.goodkarmaprojects.feature.login.data.transformer.DataTransformerImpl
import org.goodkarmaprojects.feature.login.domain.usecases.AuthRepository
import org.goodkarmaprojects.feature.login.domain.usecases.login.LoginUseCasesImpl
import org.goodkarmaprojects.feature.login.domain.usecases.register.RegisterUseCasesImpl
import org.goodkarmaprojects.feature.login.presentation.login.LoginUseCases
import org.goodkarmaprojects.feature.login.presentation.register.RegisterUseCases

@Module
class LoginModule {

    @Provides
    fun provideLoginUseCases(authRepository: AuthRepository, messageManager: MessageManager): LoginUseCases =
        LoginUseCasesImpl(authRepository, messageManager)

    @Provides
    fun provideRegisterUseCases(authRepository: AuthRepository, messageManager: MessageManager): RegisterUseCases =
        RegisterUseCasesImpl(authRepository, messageManager)

    @Provides
    fun provideLoginRepository(
        firebaseAuth: FirebaseAuth, dataTransformer: DataTransformer): AuthRepository =
        AuthRepositoryImpl(firebaseAuth, dataTransformer)

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        val auth = FirebaseAuth.getInstance()
        auth.useAppLanguage()
        return auth
    }

    @Provides
    fun provideLoginDataTransformer(): DataTransformer = DataTransformerImpl()

    @Provides
    fun provideMainDispatcher(): MainCoroutineDispatcher = Dispatchers.Main
}