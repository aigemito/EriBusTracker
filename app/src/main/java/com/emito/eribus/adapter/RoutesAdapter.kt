package com.emito.eribus.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.emito.eribus.R
import com.emito.eribus.model.Routes
import com.emito.eribus.model.Users

class RoutesAdapter(var context: Context, var routes:ArrayList<Routes>): RecyclerView.Adapter<RoutesAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var routeCode: TextView=itemView.findViewById<TextView>(R.id.tvRouteCode)
        var routeFrom: TextView=itemView.findViewById<TextView>(R.id.tvRouteFrom)
        var routeTo: TextView=itemView.findViewById<TextView>(R.id.tvRouteTo)
        var editUserImageView:ImageView =itemView.findViewById<ImageView>(R.id.editUserImageView)
        var deleteUserImageView:ImageView =itemView.findViewById<ImageView>(R.id.deleteUserImageView)

        var parentlayout: ConstraintLayout =itemView.findViewById<ConstraintLayout>(R.id.clRoute)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v =LayoutInflater.from(parent?.context).inflate(R.layout.activity_routes_viewcard,parent,false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return routes.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder?.routeCode.text=routes[position].routeCode
        holder?.routeFrom.text= routes[position].routefrom
        holder?.routeTo.text= routes[position].routeTo

       // holder?.userPicture.setImageResource(users[position].Picture.toInt())
        holder.deleteUserImageView.setOnClickListener{
            Toast.makeText(context,"Delete Clicked",Toast.LENGTH_LONG).show()

        }
        holder.editUserImageView.setOnClickListener{
            Toast.makeText(context,"Edit Clicked",Toast.LENGTH_LONG).show()

        }
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