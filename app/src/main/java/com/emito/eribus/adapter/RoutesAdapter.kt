package com.emito.eribus.adapter

import android.app.Dialog
import android.content.Context
import android.view.*
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.emito.eribus.R
import com.emito.eribus.model.Routes
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RoutesAdapter(var context: Context, var routes:ArrayList<Routes>): RecyclerView.Adapter<RoutesAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var routeCode: TextView=itemView.findViewById<TextView>(R.id.tvRouteCode)
        var routeFrom: TextView=itemView.findViewById<TextView>(R.id.tvRouteFrom)
        var routeTo: TextView=itemView.findViewById<TextView>(R.id.tvRouteTo)
        var editUserImageView:ImageView =itemView.findViewById<ImageView>(R.id.editUserImageView)
        var deleteUserImageView:ImageView =itemView.findViewById<ImageView>(R.id.deleteUserImageView)
        var btnAddBus:TextView =itemView.findViewById<TextView>(R.id.btnAddBus)
        var btnAddDriver:TextView =itemView.findViewById<TextView>(R.id.btnAddDriver)

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
        holder.btnAddBus.setOnClickListener{
//            var intent=Intent(context, AddDriverRouteActivity::class.java)
//            context.startActivity(intent)
            showAddBus(routes[position].routeId)
        }
        holder.btnAddDriver.setOnClickListener{
            showAddDriver(routes[position].routeId)

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
    private fun showAddBus(routeId:String) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        dialog.setContentView(R.layout.activity_add_bus_route)
        dialog.setCancelable(true)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        val plateNumberSpinner = dialog.findViewById<Spinner>(R.id.spinner) as Spinner
        val ref= FirebaseDatabase.getInstance().getReference("Bus")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) { // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                val buses: MutableList<String> =
                    ArrayList()
                for (plateSnapshot in dataSnapshot.children) {
                    val plateNumber =
                        plateSnapshot.child("busPlateNumber").getValue(
                            String::class.java
                        )!!
                    buses.add(plateNumber)
                }
                val busAdapter = ArrayAdapter(
                    dialog.context,
                    android.R.layout.simple_spinner_item,
                    buses
                )
                busAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                plateNumberSpinner.adapter = busAdapter
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })

        (dialog.findViewById<View>(R.id.btnrouteCreateCancel) as Button).setOnClickListener { dialog.dismiss() }
        (dialog.findViewById<View>(R.id.btnrouteCreateSubmit) as Button).setOnClickListener {
            val ref= FirebaseDatabase.getInstance().getReference("Routes")
                .child(routeId.toString()).child("Bus")

            ref.setValue(plateNumberSpinner.getSelectedItem().toString())

                    .addOnSuccessListener {
                        //notify successuflly saved user to database
                        Toast.makeText(context, "Successfully added bus to route.",
                            Toast.LENGTH_SHORT).show()

                    }
            dialog.dismiss()
            //Toast.makeText(this, "Data Successfully saved.", Toast.LENGTH_LONG).show()
        }
        dialog.show()
        dialog.window!!.attributes = lp
    }
    private fun showAddDriver(routeId:String) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        dialog.setContentView(R.layout.activity_add_driver_route)
        dialog.setCancelable(true)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        val driverSpinner = dialog.findViewById<Spinner>(R.id.driverSpinner) as Spinner
        val ref= FirebaseDatabase.getInstance().getReference("Users").orderByChild("UserType")
            .equalTo("Driver")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) { // Is better to use a List, because you don't know the size
// of the iterator returned by dataSnapshot.getChildren() to
// initialize the array
                val buses: MutableList<String> =
                    ArrayList()
                for (busSnapshot in dataSnapshot.children) {
                    val emailName =
                        busSnapshot.child("email").getValue(
                            String::class.java
                        )!!
                    buses.add(emailName)
                }
                //val busSpinner = findViewById(R.id.spinner) as Spinner
                val busAdapter = ArrayAdapter(
                    dialog.context,
                    android.R.layout.simple_spinner_item,
                    buses
                )
                busAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                driverSpinner.adapter = busAdapter
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })

        (dialog.findViewById<View>(R.id.btnrouteCreateCancel) as Button).setOnClickListener { dialog.dismiss() }
        (dialog.findViewById<View>(R.id.btnrouteCreateSubmit) as Button).setOnClickListener {
            val ref= FirebaseDatabase.getInstance().getReference("Routes")
                .child(routeId.toString()).child("Driver")

            ref.setValue(driverSpinner.getSelectedItem().toString())

                .addOnSuccessListener {
                    //notify successuflly saved user to database
                    Toast.makeText(context, "Successfully added bus to route.",
                        Toast.LENGTH_SHORT).show()

                }
            dialog.dismiss()
            //Toast.makeText(this, "Data Successfully saved.", Toast.LENGTH_LONG).show()
        }
        dialog.show()
        dialog.window!!.attributes = lp
    }
}