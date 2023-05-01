package com.example.theplantdoctor

import android.content.Intent
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
                                private var plantList: List<Plant>):
                                RecyclerView.Adapter<RecyclerPlant.MyViewHolder>() {

    fun setfilteredList(plantList: List<Plant>){
        this.plantList=plantList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.homelayout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.plt1.setImageResource(plantList[position].image)
        holder.imgTitle.text = plantList[position].title

    }
    override fun getItemCount(): Int {
        return plantList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val plt1: ImageView = itemView.findViewById(R.id.plt1)
        val imgTitle: TextView = itemView.findViewById(R.id.Title)
        val item1: CardView = itemView.findViewById(R.id.item1)

        init {
            itemView.setOnClickListener {v: View ->
                val position: Int = adapterPosition
                val intent = Intent(itemView.context, PlantDetails::class.java)
                intent.putExtra("imageId",plantList[position].image)
                intent.putExtra("title", plantList[position].title)
                itemView.context.startActivity(intent)
            }
        }
    }
}