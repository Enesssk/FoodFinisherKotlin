package com.example.foodfinisherapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodfinisherapp.R
import com.example.foodfinisherapp.databinding.FragmentInfoBinding
import com.example.foodfinisherapp.ui.adapter.InfoAdapter
import com.example.foodfinisherapp.ui.viewmodel.MainViewModel

class InfoFragment : Fragment() {

    private lateinit var binding : FragmentInfoBinding
    private lateinit var viewModel : MainViewModel
    private lateinit var infoAdapter : InfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel : MainViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInfoBinding.inflate(inflater,container,false)
        infoAdapter = InfoAdapter()

        viewModel.showInfo(requireContext()).observe(viewLifecycleOwner,){
            infoAdapter.infoList = it
            infoAdapter.notifyDataSetChanged()
        }

        binding.recyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.recyclerView.adapter=infoAdapter

        return binding.root
    }


}