package com.example.theplantdoctor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TeamRecyclerAdapter: RecyclerView.Adapter<TeamRecyclerAdapter.ViewHolder>() {

    private var images = arrayOf(R.drawable.clauvet_yome, R.drawable.xaviera_georges, R.drawable.naomi, R.drawable.berthold_draxler, R.drawable.tony_brown)
    private var names = arrayOf("Clauvet Yome", "Xaviera Georges", "Naomi Wamo", "Berthold Draxler", "Tony Brown")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamRecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.other_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.teamName.text = names[position]
        holder.teamPicture.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return names.size
    }

//    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
//        holder.teamName.text = titles[position]
//        holder.teamPicture.setImageResource(images[position])
//    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var teamPicture: ImageView
        var teamName: TextView

        init {
            teamPicture = itemView.findViewById(R.id.iv_team_picture)
            teamName = itemView.findViewById(R.id.tv_team_name)
        }
    }
}