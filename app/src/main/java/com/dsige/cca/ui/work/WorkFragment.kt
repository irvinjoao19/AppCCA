package com.dsige.cca.ui.work

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsige.cca.R
import com.dsige.cca.annotation.StatusKey.Companion.PHOTO
import com.dsige.cca.annotation.StatusKey.Companion.VIDEO
import com.dsige.cca.data.model.RegisterWork
import com.dsige.cca.databinding.FragmentWorkBinding
import com.dsige.cca.ui.adapter.WorkAdapter
import com.dsige.cca.ui.bases.baseFragment.BaseFragment
import com.dsige.cca.viewModel.WorkViewModel
import com.leinardi.android.speeddial.SpeedDialActionItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WorkFragment : BaseFragment() {

    private var _binding: FragmentWorkBinding? = null
    private val binding get() = _binding

    private val workViewModel: WorkViewModel by viewModels()
    private var workAdapter: WorkAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWorkBinding.inflate(inflater, container, false)
        setupRecycler()
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        subscribeObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
    }

    private fun setupRecycler() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding?.rvWork?.itemAnimator = DefaultItemAnimator()
        binding?.rvWork?.layoutManager = layoutManager
        workAdapter = WorkAdapter(mutableListOf(), listener = {

        })
        binding?.rvWork?.adapter = workAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun subscribeObservers() {
        //list register work
        lifecycleScope.launch {
            workViewModel.registerWork.collect { m ->
                workAdapter?.dataList = m as MutableList<RegisterWork>
                workAdapter?.notifyDataSetChanged()
            }
        }
    }

    private fun bindUI() {
        binding?.speedDial?.addActionItem(
            SpeedDialActionItem.Builder(R.id.fab_video, R.drawable.ic_play)
                .create()
        )

        binding?.speedDial?.addActionItem(
            SpeedDialActionItem.Builder(R.id.fab_photo, R.drawable.ic_menu_camera)
                .create()
        )
        setOnClickListeners()

        workViewModel.setRegisterWork()
    }

    private fun setOnClickListeners() {
        binding?.speedDial?.setOnClickListener { actionItem ->
            when (actionItem.id) {
                R.id.fab_video -> openOptionFragment(VIDEO)
                R.id.fab_photo -> openOptionFragment(PHOTO)
            }
        }
    }

    private fun openOptionFragment(type: Int) {
        val action = WorkFragmentDirections.actionWorkFragmentToOptionWorkFragment()
        findNavController().navigate(action)
    }
}