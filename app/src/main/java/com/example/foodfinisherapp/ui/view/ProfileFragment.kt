package com.example.foodfinisherapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.foodfinisherapp.R
import com.example.foodfinisherapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater,container,false)

        if(auth.currentUser != null){
            binding.useremailText.text = auth.currentUser!!.email.toString()
        }


        binding.signOutButton.setOnClickListener {
            auth.signOut()
            Navigation.findNavController(it).navigate(ProfileFragmentDirections.actionProfileFragmentToLoginFragment2())
        }


        return binding.root
    }


}