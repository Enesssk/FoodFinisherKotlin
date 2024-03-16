package com.example.foodfinisherapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodfinisherapp.R
import com.example.foodfinisherapp.data.model.Chats
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatHolder>() {

    private val sendMessage=1
    private val receivedMessage=2

    class ChatHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

    }


    override fun getItemViewType(position: Int): Int {
        val chat=chats.get(position)

        if(chat.text == FirebaseAuth.getInstance().currentUser!!.email.toString()){
            return sendMessage
        }else{
            return receivedMessage
        }

    }

    private val diffUtil= object : DiffUtil.ItemCallback<Chats>() {
        override fun areItemsTheSame(oldItem: Chats, newItem: Chats): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Chats, newItem: Chats): Boolean {
            return oldItem==newItem
        }
    }

    private val recyclerListDiffer=AsyncListDiffer(this,diffUtil)

    var chats : List<Chats>
        get() = recyclerListDiffer.currentList
        set(value)=recyclerListDiffer.submitList(value)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {

        if(viewType==sendMessage){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_right, parent, false)
            return ChatHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)
            return ChatHolder(view)
        }



    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        val user = holder.itemView.findViewById<TextView>(R.id.currentUserText)
        user.text=chats.get(position).text
        val message=holder.itemView.findViewById<TextView>(R.id.sendText)
        message.text=chats.get(position).email
    }

    override fun getItemCount(): Int {
        return chats.size
    }

}