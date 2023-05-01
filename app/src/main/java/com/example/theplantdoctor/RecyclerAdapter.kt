package com.example.theplantdoctor

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.io.ByteArrayOutputStream

class RecyclerAdapter(private var titles: List<String>, private var details: List <String>, private var images: List<Int>):
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val diseaseTitle: TextView = itemView.findViewById(R.id.tv_disease_title)
        val diseaseDetail: TextView = itemView.findViewById(R.id.tv_disease_desc)
        val diseaseTreatment: TextView = itemView.findViewById(R.id.tv_treatment)
        val diseasePicture: ImageView = itemView.findViewById(R.id.iv_disease_pic)

        init {
            itemView.setOnClickListener {v: View ->
                val position: Int = adapterPosition
                val bitmap = (diseasePicture.drawable as BitmapDrawable).bitmap
                val intent = Intent(itemView.context, Treatment::class.java)
                intent.putExtra("title", diseaseTitle.text)
                intent.putExtra("description", diseaseDetail.text)
                intent.putExtra("treatment", diseaseTreatment.text)
                intent.putExtra("imageId",images[position])

                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.disease_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.diseaseTitle.text = titles[position]
        holder.diseaseDetail.text = details[position]
        holder.diseaseTreatment.text = details[position]
        holder.diseasePicture.setImageResource(images[position])
    }
}