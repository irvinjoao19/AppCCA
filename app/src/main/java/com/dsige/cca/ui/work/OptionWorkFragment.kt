package com.dsige.cca.ui.work

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dsige.cca.R
import com.dsige.cca.databinding.FragmentOptionWorkBinding
import com.dsige.cca.ui.bases.baseFragment.BaseBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OptionWorkFragment : BaseBottomSheetFragment() {

    private var _binding: FragmentOptionWorkBinding? = null
    private val binding get() = _binding

    private var typeOption: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOptionWorkBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
    }

    private fun bindUI() {

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding?.btnContinue?.setOnClickListener {
            openFormVideoFragment()
        }
    }

    private fun openFormVideoFragment() {
        val action = OptionWorkFragmentDirections.actionOptionWorkFragmentToFormVideoFragment()
        findNavController().navigate(action)
    }

    private fun openFormPhotoFragment() {
        val action = OptionWorkFragmentDirections.actionOptionWorkFragmentToFormPhotoFragment()
        findNavController().navigate(action)
    }
}