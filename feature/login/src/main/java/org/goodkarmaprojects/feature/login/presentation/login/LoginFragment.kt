package org.goodkarmaprojects.feature.login.presentation.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.play.core.splitcompat.SplitCompat
import org.goodkarmaprojects.feature.login.databinding.FragmentLoginBinding
import org.goodkarmaprojects.feature.login.di.inject
import javax.inject.Inject
import org.goodkarmaprojects.cleanup.R as AppR


class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[LoginViewModel::class.java]
    }

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false).apply {
            viewModel = loginViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        binding.loginButton.setOnClickListener { loginViewModel.doLogin() }

        binding.forgottenPasswordText.setOnClickListener {
            findNavController().navigate(AppR.id.action_loginFragment_to_forgottenPasswordDialog)
        }

        binding.registerText.setOnClickListener {
            findNavController().navigate(AppR.id.action_loginFragment_to_registerFragment)
        }

        loginViewModel.loginResult.observe(viewLifecycleOwner){
            it?.let {
                if(it.verified){
                    goToHome()
                } else {
                    showNotVerifiedEmailDialog()
                }
            }
        }

        loginViewModel.getCurrentUser()?.let {
            if(it.verified) goToHome()
        }

        return binding.root
    }

    private fun showNotVerifiedEmailDialog() {
        findNavController().navigate(AppR.id.action_loginFragment_to_loginVerifyEmailDialog)
    }

    private fun goToHome() = findNavController().navigate(AppR.id.action_loginFragment_to_homeFragment)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        SplitCompat.install(requireActivity())
        inject()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        loginViewModel.loginResult.postValue(null)
    }

}