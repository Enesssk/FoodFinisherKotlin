package com.example.foodfinisherapp.data.repo

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.foodfinisherapp.data.datasource.MainDataSource
import com.example.foodfinisherapp.data.model.Info

class MainRepo {

    var mds = MainDataSource()

    fun saveInfo(food : String, date: String, gram : String,mContext : Context,view: View){
        mds.saveInfo(food, date, gram, mContext,view)
    }

    fun showInfo(mContext: Context) : MutableLiveData<List<Info>> {
        return mds.showInfo(mContext)
    }

}