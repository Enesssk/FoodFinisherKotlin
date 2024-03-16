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
import com.example.foodfinisherapp.data.model.Info
import org.w3c.dom.Text

class InfoAdapter : RecyclerView.Adapter<InfoAdapter.InfoHolder>() {

    class InfoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    private val diffUtil = object : DiffUtil.ItemCallback<Info>() {
        override fun areItemsTheSame(oldItem: Info, newItem: Info): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Info, newItem: Info): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var infoList: List<Info>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_info, parent, false)
        return InfoHolder(view)
    }

    override fun getItemCount(): Int {
        return infoList.size
    }

    override fun onBindViewHolder(holder: InfoHolder, position: Int) {
        val foodName = holder.itemView.findViewById<TextView>(R.id.foodNameText)
        foodName.text = infoList.get(position).food

        val date = holder.itemView.findViewById<TextView>(R.id.dateText)
        date.text = infoList.get(position).date

        val gram = holder.itemView.findViewById<TextView>(R.id.gramText)
        gram.text = infoList.get(position).gram
    }


}