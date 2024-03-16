package com.example.foodfinisherapp.ui.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodfinisherapp.data.model.Info
import com.example.foodfinisherapp.data.repo.MainRepo

class MainViewModel : ViewModel() {

    var mRepo = MainRepo()

    fun saveInfo(food : String, date: String, gram : String,mContext : Context,view: View){
        mRepo.saveInfo(food, date, gram, mContext,view)
    }

    fun showInfo(mContext: Context) : MutableLiveData<List<Info>> {
        return mRepo.showInfo(mContext)
    }


}