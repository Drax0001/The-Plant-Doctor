package com.example.theplantdoctor

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView

//Recycler Adapater of the Newhome

class RecyclerPlant constructor(private val getActivity: Homepage,
                                private val plantList: List<Plant>):
                                RecyclerView.Adapter<RecyclerPlant.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.homelayout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.plt1.setImageResource(plantList[position].image)
    }
    override fun getItemCount(): Int {
        return plantList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val plt1: ImageView = itemView.findViewById(R.id.plt1)
        val item1: CardView = itemView.findViewById(R.id.item1)
    }
}