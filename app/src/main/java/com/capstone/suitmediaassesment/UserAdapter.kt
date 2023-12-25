package com.capstone.suitmediaassesment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.suitmediaassesment.model.DataItem

class UserAdapter() :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    val list = ArrayList<DataItem>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val firstName: TextView = view.findViewById(R.id.tvFirstName)
        val lastName: TextView = view.findViewById(R.id.tvLastName)
        val email: TextView = view.findViewById(R.id.tvEmail)
        val photo: ImageView = view.findViewById(R.id.ivProfile)
        val cardView: CardView = view.findViewById(R.id.cardViewItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = list[position]
        holder.apply {
            firstName.text = user.firstName
            lastName.text = user.lastName
            email.text = user.email
            Glide.with(itemView)
                .load(user.avatar)
                .into(photo)

            cardView.setOnClickListener {
                val intent = Intent(itemView.context, SecondActivity::class.java)
                intent.putExtra("firstName", user.firstName)
                intent.putExtra("lastName", user.lastName)
                intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(itemView.context, intent, null)
            }
        }

    }
    fun addList(listitem: ArrayList<DataItem>) {
        list.addAll(listitem)
        notifyDataSetChanged()
    }
    fun clearList() {
        list.clear()
        notifyDataSetChanged()
    }


}