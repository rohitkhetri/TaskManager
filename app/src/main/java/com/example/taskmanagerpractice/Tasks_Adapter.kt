package com.example.taskmanagerpractice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Tasks_Adapter(private val data: ArrayList<Tasks>) :
    RecyclerView.Adapter<Tasks_Adapter.ViewHolder>() {

    inner class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtCategory : TextView
        var txtTitle : TextView
        var txtDate : TextView
        var imgview : ImageView

        init {
            txtCategory = itemView.findViewById(R.id.txtCategory)
            txtTitle = itemView.findViewById(R.id.txtTitle)
            txtDate = itemView.findViewById(R.id.txtDateView)
            imgview = itemView.findViewById(R.id.imgview)
        }
    }

    override fun getItemCount() : Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.imgview.setImageResource(item.image)
        holder.txtCategory.setText("Category : ${item.title}")
        holder.txtTitle.setText("Title : ${item.price}")
        holder.txtDate.setText("Date : ${item.date}")
    }
}