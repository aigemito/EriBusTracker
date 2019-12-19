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

import com.emito.eribus.R
import com.emito.eribus.activity.RouteDetailActivity

/**
 * A simple [Fragment] subclass.
 */
class DriverSchedulesFragment : Fragment() {
    lateinit var listView: ListView
    lateinit var adapter: ArrayAdapter<String>
    lateinit var routeList : Array<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var rootView: View = inflater.inflate(R.layout.fragment_driver_schedules, container, false)
        listView = rootView.findViewById<ListView>(R.id.driverSchedules)
        var routeList = arrayOf("Bob", "Alik", "Bob", "Alik", "Bob", "Alik","Bob", "Alik","Bob", "Alik","Bob", "Alik","Bob", "Alik")
        val adapter = ArrayAdapter<String>(rootView.context, android.R.layout.simple_spinner_dropdown_item, routeList)

        listView.adapter = adapter

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->

                val item = parent.getItemAtPosition(position).toString()
                var intent = Intent(rootView.context, RouteDetailActivity::class.java)
                var deptLat = 41.921673
                var deptLong =  -93.312271
                var destLat = 37.778008
                var destLong = -122.431272

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
