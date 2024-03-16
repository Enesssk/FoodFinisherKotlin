package com.example.foodfinisherapp.data.datasource

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.example.foodfinisherapp.data.model.Chats
import com.example.foodfinisherapp.data.model.Info
import com.example.foodfinisherapp.ui.view.MainScreenFragmentDirections
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainDataSource {

    lateinit var auth : FirebaseAuth
    lateinit var firestore : FirebaseFirestore
    private var infoList= arrayListOf<Info>()



    fun saveInfo(food : String, date: String, gram : String,mContext : Context,view: View){

        auth = Firebase.auth
        firestore = Firebase.firestore

        var food = food
        var date = date
        var gram = gram

        val infoPost = HashMap<String,Any>()
        infoPost.put("food",food)
        infoPost.put("date",date)
        infoPost.put("gram",gram)

        val info = firestore.collection("Info").add(infoPost).addOnFailureListener(){
            Toast.makeText(mContext,it.localizedMessage,Toast.LENGTH_LONG).show()
        }.addOnSuccessListener {
            Toast.makeText(mContext,"Success",Toast.LENGTH_LONG).show()
            Navigation.findNavController(view).navigate(MainScreenFragmentDirections.actionMainScreenFragmentToInfoFragment())
        }

    }

    fun showInfo(mContext: Context) : MutableLiveData<List<Info>> {

        auth = Firebase.auth
        firestore = Firebase.firestore

        val infoLiveData = MutableLiveData<List<Info>>()

        firestore.collection("Info").orderBy("date", Query.Direction.ASCENDING).addSnapshotListener { value, error ->
            if (error != null) {
                Toast.makeText(mContext, error.localizedMessage, Toast.LENGTH_LONG).show()
            }

            if (value != null) {
                if (value.isEmpty) {
                    Toast.makeText(mContext, "Error sorry..", Toast.LENGTH_LONG).show()
                } else {
                    val documents = value.documents
                    infoList.clear()

                    for(document in documents){
                        val food = document.get("food") as String
                        val date = document.get("date") as String
                        val gram = document.get("gram") as String

                        val info = Info(food, date, gram)
                        infoList.add(info)
                        infoLiveData.value = infoList
                    }
                }

            }
        }
        return infoLiveData

    }


}