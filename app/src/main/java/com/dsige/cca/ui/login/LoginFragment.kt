package com.dsige.cca.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dsige.cca.R
import com.dsige.cca.databinding.FragmentLoginBinding
import com.dsige.cca.extensions.ContextExtensions.toastMensaje
import com.dsige.cca.extensions.FragmentExtensions.hideKeyboard
import com.dsige.cca.ui.bases.baseFragment.BaseFragment
import com.dsige.cca.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding

    private val loginViewModel: LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        subscribeObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
    }


    private fun setOnClickListeners() {
        binding?.btnLogin?.setOnClickListener {
            formLogin()
        }
    }

    private fun formLogin() {
        val user = binding?.tilUser?.text.toString().trim()
        val pass = binding?.tilPass?.text.toString().trim()

        if (user.isEmpty()) {
            loginViewModel.setError("Ingrese usuario")
            return
        }
        if (pass.isEmpty()) {
            loginViewModel.setError("Ingrese password")
            return
        }

        this.hideKeyboard()
        viewLoading(true, getString(R.string.login))

        val model = HashMap<String, Any>()
        model["user"] = user
        model["pass"] = pass
        loginViewModel.setLogin(model)
    }


    private fun subscribeObservers() {
        //error
        lifecycleScope.launch {
            loginViewModel.mensajeError.collect { m ->
                m?.let {
                    viewLoading(false, "")
                    requireContext().toastMensaje(it)
                }
            }
        }
        //success
        lifecycleScope.launch {
            loginViewModel.mensajeSuccess.collect { m ->
                m?.let {
                    viewLoading(false, "")
                    openMainActivity()
                }
            }
        }
    }

    private fun openMainActivity() {
        val action = LoginFragmentDirections.actionLoginFragmentToMainActivity()
        findNavController().navigate(action)
    }
}