package com.harjot.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapterClass(var array: ArrayList<UserEntity>,var notesInterface: NotesInterface ):
    RecyclerView.Adapter<RecyclerAdapterClass.ViewHolder>(){
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var Text=view.findViewById<TextView>(R.id.tvText)
        var Title= view.findViewById<TextView>(R.id.tvTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.Title.setText(array[position].Title)
        holder.Text.setText(array[position].Text)
        holder.itemView.setOnClickListener {
            notesInterface.click(array[position])
        }
    }
}