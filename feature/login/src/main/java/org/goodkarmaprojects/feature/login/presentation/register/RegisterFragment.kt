package org.goodkarmaprojects.feature.login.presentation.register

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.play.core.splitcompat.SplitCompat
import org.goodkarmaprojects.feature.login.databinding.FragmentRegisterBinding
import org.goodkarmaprojects.feature.login.di.inject
import javax.inject.Inject
import org.goodkarmaprojects.cleanup.R as AppR


class RegisterFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val registerViewModel: RegisterViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[RegisterViewModel::class.java]
    }

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false).apply {
            viewModel = registerViewModel
            lifecycleOwner = viewLifecycleOwner
            setLegalClickListener {
                openTermsAndConditions()
            }
            setBackClickListener {
                findNavController().navigateUp()
            }
        }

        binding.registerButton.setOnClickListener {
            registerViewModel.doRegister()
        }

        registerViewModel.registerResult.observe(viewLifecycleOwner){
            if(it != null){
                openVerifyEmailDialog()
            }
        }

        registerViewModel.dismissedVerifyDialog.observe(viewLifecycleOwner){
            if(it) findNavController().navigateUp()
        }

        return binding.root
    }

    private fun openVerifyEmailDialog() = findNavController().navigate(
        AppR.id.action_registerFragment_to_registerVerifyEmailDialog)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        SplitCompat.install(requireActivity())
        inject()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        registerViewModel.registerResult.postValue(null)
        registerViewModel.dismissedVerifyDialog.postValue(false)
    }

    private fun openTermsAndConditions(){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://goodkarmaprojects.org/politica-de-privacidad/"))
        startActivity(browserIntent)
    }

}