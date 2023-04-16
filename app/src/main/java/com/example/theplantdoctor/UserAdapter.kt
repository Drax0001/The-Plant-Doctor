package com.example.theplantdoctor

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class UserAdapter(val context: Context, val userList: ArrayList<User>):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.user_layout, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]

        holder.textUsers.text = currentUser.name

        holder.itemView.setOnClickListener {
            val intent = Intent(context, Chat::class.java)

            intent.putExtra("name", currentUser.name)
            intent.putExtra("uid", currentUser.uid)

            context.startActivity(intent)
        }
    }


    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textUsers: TextView = itemView.findViewById<TextView>(R.id.tv_users)
    }

//    MessageAdapter mAdapt = new(MessageAdapter):
//    val recent = mAdapt.currentMessage

}