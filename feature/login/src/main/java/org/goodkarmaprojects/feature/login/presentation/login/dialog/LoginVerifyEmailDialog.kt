package org.goodkarmaprojects.feature.login.presentation.login.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.play.core.splitcompat.SplitCompat
import org.goodkarmaprojects.feature.login.databinding.LoginVerifyEmailDialogBinding
import org.goodkarmaprojects.feature.login.di.inject
import org.goodkarmaprojects.feature.login.presentation.login.LoginViewModel
import javax.inject.Inject

class LoginVerifyEmailDialog : DialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[LoginViewModel::class.java]
    }

    private lateinit var binding: LoginVerifyEmailDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val inflater = requireActivity().layoutInflater
            binding = LoginVerifyEmailDialogBinding.inflate(inflater)
            binding.setResendCodeClickListener {
                loginViewModel.resendVerifyLink()
                dismiss()
            }
            binding.setCloseClickListener {
                dismiss()
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
}
