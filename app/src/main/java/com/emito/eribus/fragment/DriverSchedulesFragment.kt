package com.emito.eribus.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

import com.emito.eribus.R
import com.emito.eribus.activity.RouteDetailActivity
import com.emito.eribus.model.Routes
import com.emito.eribus.utils.Auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * A simple [Fragment] subclass.
 */
class DriverSchedulesFragment : Fragment() {
    lateinit var listView: ListView
    lateinit var adapter: ArrayAdapter<String>
    lateinit var routeList : Array<String>
    lateinit var routeCode:String
    lateinit var routeFrom:String
    lateinit var routeTo:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var rootView: View = inflater.inflate(R.layout.fragment_driver_schedules, container, false)
        listView = rootView.findViewById<ListView>(R.id.driverSchedules)

        val ref= FirebaseDatabase.getInstance().getReference("Routes").orderByChild("Driver")
            .equalTo(Auth.currentUser.Email)
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) { // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                val routeList: MutableList<String> =
                    ArrayList()
                for (plateSnapshot in dataSnapshot.children) {
                     routeCode =
                        plateSnapshot.child("routeCode").getValue(
                            String::class.java
                        )!!
                     routeFrom =
                        plateSnapshot.child("routefrom").getValue(
                            String::class.java
                        )!!
                     routeTo =
                        plateSnapshot.child("routeTo").getValue(
                            String::class.java
                        )!!
                    routeList.add("Route Code:$routeCode | From:$routeFrom | To:$routeTo")
                }
                adapter = ArrayAdapter<String>(rootView.context, android.R.layout.simple_spinner_dropdown_item, routeList)

                listView.adapter = adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })


        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->

                val item = parent.getItemAtPosition(position).toString().split("|")
                //Toast.makeText(context, item[0],Toast.LENGTH_LONG).show()
                var intent = Intent(rootView.context, RouteDetailActivity::class.java)
                var deptLat = 41.921673
                var deptLong =  -93.312271
                var destLat = 37.778008
                var destLong = -122.431272

                intent.putExtra("routeCode", item[0])
                intent.putExtra("routeFrom", item[1])
                intent.putExtra("routeTo", item[2])
                intent.putExtra("deptLat", deptLat)
                intent.putExtra("deptLong", deptLong)
                intent.putExtra("destLat", destLat)
                intent.putExtra("destLong", destLong)

                startActivity(intent)
            }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState:Bundle?){
        super.onViewCreated(view, savedInstanceState)

    }

}
