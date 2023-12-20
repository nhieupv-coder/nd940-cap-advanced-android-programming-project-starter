package com.example.android.politicalpreparedness.election

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.politicalpreparedness.databinding.FragmentElectionBinding
import com.example.android.politicalpreparedness.election.adapter.ElectionListAdapter
import com.example.android.politicalpreparedness.election.adapter.ElectionListener

class ElectionsFragment : Fragment() {

    private val viewModel: ElectionsViewModel by lazy {
        ViewModelProvider(this)[ElectionsViewModel::class.java]
    }
    private lateinit var binding: FragmentElectionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentElectionBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.upcomingElectionsRecycler.adapter = ElectionListAdapter(ElectionListener {
            viewModel.goToDetailPage(it)
        })

        binding.savedElectionsRecycler.adapter = ElectionListAdapter(ElectionListener {
            viewModel.goToDetailPage(it)
        })

        viewModel.navigateData.observe(viewLifecycleOwner) {
            it?.let {
                this.findNavController().navigate(
                    ElectionsFragmentDirections.actionElectionsFragmentToVoterInfoFragment(it)
                )
                viewModel.setNullNav()
            }
        }
        return binding.root
    }
}