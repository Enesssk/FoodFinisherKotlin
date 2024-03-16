package com.example.foodfinisherapp.data.repo

import android.content.Context
import android.view.View
import com.example.foodfinisherapp.data.datasource.LoginDataSource

class LoginRepository {

    var lds = LoginDataSource()

    fun signUp(email : String, password : String, mcontext: Context, view: View) {
        lds.signUp(email, password, mcontext, view)
    }

    fun signIn(email : String, password : String, mcontext: Context, view: View) {
        lds.signIn(email, password, mcontext, view)
    }

}