package com.emito.eribus.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.emito.eribus.R
import com.emito.eribus.login.UserDetailListActivity
import com.emito.eribus.model.Users

class UserProfileAdapter(var context: Context,var users:ArrayList<Users>): RecyclerView.Adapter<UserProfileAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var userName: TextView=itemView.findViewById<TextView>(R.id.tvUserName)
        var userEmail: TextView=itemView.findViewById<TextView>(R.id.tvEmail)
        var userType: TextView=itemView.findViewById<TextView>(R.id.tvUserType)
        var userPicture: ImageView =itemView.findViewById<ImageView>(R.id.ivUserProfile)
        var parentlayout: ConstraintLayout =itemView.findViewById<ConstraintLayout>(R.id.clUserProfileDetail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v =LayoutInflater.from(parent?.context).inflate(R.layout.activity_user_viewcard,parent,false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return users.count()
    }

    override fun onBindViewHolder(holder: UserProfileAdapter.MyViewHolder, position: Int) {

        holder?.userName.text=users[position].userName
        holder?.userEmail.text="Price : $" + users[position].Email
        holder?.userType.text="Color : " + users[position].UserType
       // holder?.userPicture.setImageResource(users[position].Picture.toInt())

//        holder.parentlayout.setOnClickListener{
//            val intent=Intent(context, UserDetailListActivity::class.java)
//            var user=Users(users[position].userName,users[position].FullName,users.,Users[position].Price,
//                Users[position].Color,Users[position].Image.toString(),
//                Users[position].Desc)
//            intent.putExtra("SelectedProduct",product)
//            context.startActivity(intent)
//        }
    }

}