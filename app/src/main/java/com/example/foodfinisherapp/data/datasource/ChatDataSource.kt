package com.example.foodfinisherapp.data.datasource

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.foodfinisherapp.data.model.Chats
import com.example.foodfinisherapp.ui.adapter.ChatAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class ChatDataSource {

    lateinit var auth : FirebaseAuth
    lateinit var firestore : FirebaseFirestore
    private var adapter= ChatAdapter()
    private var chats= arrayListOf<Chats>()


    fun sendMessage(mContext : Context,text:String) {

        auth = Firebase.auth
        firestore = Firebase.firestore

        auth.currentUser?.let {

            var text = text
            val email = it.email.toString()
            val date = FieldValue.serverTimestamp()

            val post = HashMap<String , Any> ()

            post.put("text",text)
            post.put("email",email)
            post.put("date",date)

            firestore.collection("Chats").add(post).addOnFailureListener {
                Toast.makeText(mContext,it.localizedMessage,Toast.LENGTH_LONG).show()

            }.addOnSuccessListener {
                Toast.makeText(mContext,"Success",Toast.LENGTH_LONG).show()

            }


        }
    }

    fun showMessage(mContext: Context) : MutableLiveData<List<Chats>> {

        firestore=Firebase.firestore
        auth=Firebase.auth
        val messageLiveData = MutableLiveData<List<Chats>>()

        firestore.collection("Chats").orderBy("date",
            Query.Direction.ASCENDING).addSnapshotListener { value, error ->

            if(error != null){
                Toast.makeText(mContext,"Error sorry..",Toast.LENGTH_LONG).show()
            }

            if(value != null){
                if(value.isEmpty){
                    Toast.makeText(mContext,"Error sorry..",Toast.LENGTH_LONG).show()
                }else{
                    val documents=value.documents
                    chats.clear()

                    for(document in documents){
                        val textChat=document.get("text") as String
                        val user=document.get("email") as String
                        val date = document.getDate("date")

                        /*
                        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                        val dateString = dateFormat.format(date)


                         */
                        val chat=Chats(user,textChat)
                        chats.add(chat)
                        messageLiveData.value=chats
                    }

                }

            }


        }
        adapter.notifyDataSetChanged()
        return messageLiveData
    }



}