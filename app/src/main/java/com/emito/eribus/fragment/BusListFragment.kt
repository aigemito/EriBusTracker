package com.emito.eribus.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.emito.eribus.R
import com.emito.eribus.adapter.BusAdapter
import com.emito.eribus.model.Bus
import com.google.firebase.database.*

/**
 * A simple [Fragment] subclass.
 */
class BusListFragment : Fragment() {

    lateinit var routeView: View
    private lateinit var reference: DatabaseReference
    var r1: RecyclerView? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var madr: BusAdapter? = null
    private lateinit var BusList: ArrayList<Bus>
    private lateinit var btnsaveroute: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        routeView = inflater.inflate(R.layout.fragment_bus_list, container, false)

        BusList = ArrayList<Bus>()
        r1 = routeView.findViewById<RecyclerView>(R.id.busListRecyclerView)
        layoutManager = LinearLayoutManager(routeView.context)
        r1?.layoutManager = layoutManager

        loadData()

        return routeView
    }

    private fun loadData() {
        reference = FirebaseDatabase.getInstance().getReference("Bus")
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                //val Bus = dataSnapshot.getValue(Bus::class.java)
                BusList.clear()
                for (dss in dataSnapshot.children) {
                    val plateNumber = dss.child("busPlateNumber").getValue().toString()
                    val busModel = dss.child("busModel").getValue().toString()
                    val numberOfSits = dss.child("numberOfSits").getValue()
                    val bus=Bus(dss.key.toString(),plateNumber ,Integer.parseInt(numberOfSits.toString()),busModel)

                    //val route = dataSnapshot.getValue(Bus::class.java)
                    if(bus!=null)
                        BusList.add(bus)
                    // Toast.makeText(routeView.context, "This is emito" + route.toString(), Toast.LENGTH_SHORT).show()
                }


                madr = BusAdapter(routeView.context, BusList)
                r1?.adapter = madr

                //for the total Bus
                var totalBusTextView: TextView =
                    routeView.findViewById<TextView>(R.id.totalBusTextView)
                totalBusTextView.text = madr!!.itemCount.toString()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }

        reference.addListenerForSingleValueEvent(postListener)
    }


}
