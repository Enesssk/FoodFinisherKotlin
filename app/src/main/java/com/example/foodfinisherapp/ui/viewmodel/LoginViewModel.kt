package com.example.foodfinisherapp.ui.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.foodfinisherapp.data.repo.LoginRepository
import kotlin.math.log

class LoginViewModel : ViewModel() {

    var loginRepo = LoginRepository()


    fun signUp(email : String, password : String, mcontext: Context, view: View) {
         loginRepo.signUp(email, password, mcontext, view)
    }

    fun signIn(email : String, password : String, mcontext: Context, view: View) {
        loginRepo.signIn(email, password, mcontext, view)
    }




}