package com.example.foodfinisherapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodfinisherapp.data.model.Chats
import com.example.foodfinisherapp.data.repo.ChatRepository

class ChatViewModel : ViewModel() {

    var chatRepo = ChatRepository()

    fun sendMessage(mContext : Context, text:String) {
        chatRepo.sendMessage(mContext, text)
    }

    fun showMessage(mContext: Context) : MutableLiveData<List<Chats>> {
        return chatRepo.showMessage(mContext)
    }

}