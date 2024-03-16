package com.example.foodfinisherapp.data.repo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.foodfinisherapp.data.datasource.ChatDataSource
import com.example.foodfinisherapp.data.model.Chats

class ChatRepository {

    val cds = ChatDataSource()


    fun sendMessage(mContext : Context, text:String) {
        cds.sendMessage(mContext,text)
    }

    fun showMessage(mContext: Context) : MutableLiveData<List<Chats>> {
        return cds.showMessage(mContext)
    }

}