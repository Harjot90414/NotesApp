package com.harjot.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapterClass(var array: ArrayList<UserModel>, var userDao: MainActivity):
    RecyclerView.Adapter<RecyclerAdapterClass.ViewHolder>(){
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var lv= view.findViewById<LinearLayout>(R.id.lv)
        var tvText=view.findViewById<TextView>(R.id.tvText)
        var tvTitle= view.findViewById<TextView>(R.id.tvTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.setText(array[position].Title)
        holder.tvText.setText(array[position].Text)
        holder.lv.setOnClickListener {
            
        }
    }
}