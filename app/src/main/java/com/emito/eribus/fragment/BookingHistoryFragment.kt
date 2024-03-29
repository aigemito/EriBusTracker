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

/**
 * A simple [Fragment] subclass.
 */
class BookingHistoryFragment : Fragment() {

    lateinit var listView: ListView
    lateinit var adapter: ArrayAdapter<String>
    lateinit var routeList : Array<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_booking_history, container, false)
        listView = rootView.findViewById<ListView>(R.id.bookingHistory)
        var routeList = arrayOf("Bob", "Alik", "Bob", "Alik", "Bob", "Alik","Bob", "Alik","Bob", "Alik","Bob", "Alik","Bob", "Alik")
        val adapter = ArrayAdapter<String>(rootView.context, android.R.layout.simple_spinner_dropdown_item, routeList)
        listView.adapter = adapter
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val item = parent.getItemAtPosition(position).toString()
                val intent = Intent(rootView.context, RouteDetailActivity::class.java)
                startActivity(intent)
            }
        return rootView
    }


}
