package com.example.foodfinisherapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.foodfinisherapp.R
import com.example.foodfinisherapp.databinding.FragmentLoginBinding
import com.example.foodfinisherapp.ui.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    private lateinit var viewModel : LoginViewModel
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel : LoginViewModel by viewModels()
        viewModel = tempViewModel


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater,container,false)

        auth = Firebase.auth

        val currentUser = auth.currentUser

        if(currentUser != null) {
            val action = LoginFragmentDirections.actionLoginFragment2ToMainScreenFragment()
            findNavController().navigate(action)
        }


        binding.signUpButton.setOnClickListener {
            val emailText = binding.emailText.text.toString()
            val passwordText = binding.passwordText.text.toString()

            viewModel.signUp(emailText,passwordText,requireContext(),it)
        }

        binding.signInButton.setOnClickListener {
            val emailText = binding.emailText.text.toString()
            val passwordText = binding.passwordText.text.toString()

            viewModel.signIn(emailText,passwordText,requireContext(),it)
        }


        return binding.root
    }

}