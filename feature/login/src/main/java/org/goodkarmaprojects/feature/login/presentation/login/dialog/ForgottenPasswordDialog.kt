package org.goodkarmaprojects.feature.login.presentation.login.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.play.core.splitcompat.SplitCompat
import org.goodkarmaprojects.feature.login.databinding.ForgottenPasswordDialogBinding
import org.goodkarmaprojects.feature.login.databinding.LoginVerifyEmailDialogBinding
import org.goodkarmaprojects.feature.login.di.inject
import org.goodkarmaprojects.feature.login.presentation.login.LoginViewModel
import javax.inject.Inject

class ForgottenPasswordDialog : DialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[LoginViewModel::class.java]
    }

    private lateinit var binding: ForgottenPasswordDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val inflater = requireActivity().layoutInflater
            binding = ForgottenPasswordDialogBinding.inflate(inflater)
            binding.lifecycleOwner = this
            binding.viewModel = loginViewModel

            binding.setCloseClickListener {
                dismiss()
            }
            binding.setResetPasswordClickListener {
                loginViewModel.sendForgottenPasswordEmail()
            }

            loginViewModel.forgottenPasswordSuccess.observe(this){ success ->
                if(success) dismiss()
            }

            val builder = android.app.AlertDialog.Builder(it)
            builder.setView(binding.root)
            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        SplitCompat.install(requireActivity())
        inject()
    }

    override fun onDestroy() {
        super.onDestroy()
        loginViewModel.forgottenPasswordEmail.postValue("")
        loginViewModel.forgottenPasswordSuccess.postValue(false)
    }
}
