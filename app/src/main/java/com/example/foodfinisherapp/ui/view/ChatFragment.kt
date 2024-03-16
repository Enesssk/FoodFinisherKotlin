package com.example.foodfinisherapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.foodfinisherapp.R
import com.example.foodfinisherapp.databinding.FragmentChatBinding
import com.example.foodfinisherapp.ui.adapter.ChatAdapter
import com.example.foodfinisherapp.ui.viewmodel.ChatViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.*
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ChatFragment : Fragment() {

    private lateinit var binding : FragmentChatBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var firestore : FirebaseFirestore
    private lateinit var viewModel : ChatViewModel
    private lateinit var adapter:ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        firestore = Firebase.firestore

        val tempViewModel : ChatViewModel by viewModels ()
        viewModel = tempViewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(inflater,container,false)
        adapter = ChatAdapter()

        binding.sendButton.setOnClickListener {

            var text = binding.sendText.text.toString()
           viewModel.sendMessage(requireContext(),text)

        }


        viewModel.showMessage(requireContext()).observe(viewLifecycleOwner,{
            adapter.chats=it
            adapter.notifyDataSetChanged()
        })

        binding.recyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.recyclerView.adapter=adapter



        return binding.root
    }


}