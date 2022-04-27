package org.goodkarmaprojects.feature.login.presentation.register.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.play.core.splitcompat.SplitCompat
import org.goodkarmaprojects.feature.login.databinding.RegisterVerifyEmailDialogBinding
import org.goodkarmaprojects.feature.login.di.inject
import org.goodkarmaprojects.feature.login.presentation.register.RegisterViewModel
import javax.inject.Inject

class RegisterVerifyEmailDialog : DialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val registerViewModel: RegisterViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[RegisterViewModel::class.java]
    }

    private lateinit var binding: RegisterVerifyEmailDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val inflater = requireActivity().layoutInflater
            binding = RegisterVerifyEmailDialogBinding.inflate(inflater)
            binding.setClickListener { dismiss() }

            val builder = android.app.AlertDialog.Builder(it)
            builder.setView(binding.root)
            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDestroy() {
        super.onDestroy()
        registerViewModel.dismissedVerifyDialog.postValue(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        SplitCompat.install(requireActivity())
        inject()
    }
}
