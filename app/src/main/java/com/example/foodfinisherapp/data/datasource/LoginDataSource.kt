package com.example.foodfinisherapp.data.datasource

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.foodfinisherapp.ui.view.LoginFragment
import com.example.foodfinisherapp.ui.view.LoginFragmentDirections
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginDataSource {


    lateinit var auth : FirebaseAuth

    fun signUp(email : String, password : String, mcontext: Context,view: View) {


        auth = Firebase.auth

        auth.createUserWithEmailAndPassword(email, password).addOnFailureListener {

            Toast.makeText(mcontext,it.localizedMessage,Toast.LENGTH_LONG).show()

        }.addOnSuccessListener {

            if (email.isNotEmpty() || password.isNotEmpty()) {
                Navigation.findNavController(view).navigate(LoginFragmentDirections.actionLoginFragment2ToMainScreenFragment())
            }

        }
    }

    fun signIn(email : String, password : String, mcontext: Context,view: View) {

        auth = Firebase.auth

        auth.signInWithEmailAndPassword(email, password).addOnFailureListener {

            Toast.makeText(mcontext,it.localizedMessage,Toast.LENGTH_LONG).show()

        }.addOnSuccessListener {
            if(email.isNotEmpty() || password.isNotEmpty()) {
                Navigation.findNavController(view).navigate(LoginFragmentDirections.actionLoginFragment2ToMainScreenFragment())
            }
        }

    }



}