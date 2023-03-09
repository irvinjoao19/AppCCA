package com.dsige.cca.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dsige.cca.databinding.ContentLoadingBinding
import com.dsige.cca.ui.bases.baseFragment.BaseFragment
import com.dsige.cca.viewModel.CheckSessionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CheckSessionFragment : BaseFragment() {

    private var _binding: ContentLoadingBinding? = null
    private val binding get() = _binding

    private val checkSessionViewModel: CheckSessionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = ContentLoadingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkSessionViewModel.verifySignIn()
    }

    private fun subscribeObservers() {
        lifecycleScope.launch {
            checkSessionViewModel.usuario.collect {
                if (it == null) {
                    val action =
                        CheckSessionFragmentDirections.actionCheckSessionFragmentToLoginFragment()
                    findNavController().navigate(action)
                } else {
                    val action =
                        CheckSessionFragmentDirections.actionCheckSessionFragmentToMainActivity()
                    findNavController().navigate(action)
                }
            }
        }
    }
}