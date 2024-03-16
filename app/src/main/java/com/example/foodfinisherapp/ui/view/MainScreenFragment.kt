package com.example.foodfinisherapp.ui.view


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.foodfinisherapp.R

import com.example.foodfinisherapp.databinding.FragmentMainScreenBinding
import com.example.foodfinisherapp.ui.viewmodel.MainViewModel
import java.util.*


class MainScreenFragment : Fragment() {

    private lateinit var binding : FragmentMainScreenBinding
    private val channel_ID = "channel_id_example_01"
    private val notificationId = 101

    private lateinit var viewModel : MainViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel : MainViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainScreenBinding.inflate(inflater,container,false)

        createNotifiticationChannel()
        binding.saveButton.setOnClickListener {
            sendNotification()

            var food = binding.foodText.text.toString()
            var date = binding.dateText.text.toString()
            var gram = binding.gramText.text.toString()
            viewModel.saveInfo(food,date,gram,requireContext(),requireView())
        }

        binding.goChat.setOnClickListener {
            Navigation.findNavController(it).navigate(MainScreenFragmentDirections.actionMainScreenFragmentToChatFragment())
        }
        binding.goProfile.setOnClickListener {
            Navigation.findNavController(it).navigate(MainScreenFragmentDirections.actionMainScreenFragmentToProfileFragment())
        }
        binding.showInfo.setOnClickListener {
            Navigation.findNavController(it).navigate(MainScreenFragmentDirections.actionMainScreenFragmentToInfoFragment())
        }



        return binding.root
    }


    private fun createNotifiticationChannel(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Notification Title"
            val descriptionText = "Notification Description"
            val importance : Int = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channel_ID,name,importance).apply {
                description = descriptionText
            }

            val notificationManager : NotificationManager  = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }

    private fun sendNotification() {
        val builder : NotificationCompat.Builder = NotificationCompat.Builder(requireContext(),channel_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Expiration date is approaching")
            .setContentText("You should eat your foods")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(requireContext().applicationContext)){
            notify(notificationId , builder.build())
        }
    }






}