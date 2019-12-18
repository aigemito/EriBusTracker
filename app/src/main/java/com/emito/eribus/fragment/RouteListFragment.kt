package com.emito.eribus.fragment


import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emito.eribus.R
import com.emito.eribus.adapter.RoutesAdapter
import com.emito.eribus.model.Routes
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_routes_list.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class RouteListFragment : Fragment() {
    lateinit var routeView: View
    private lateinit var reference: DatabaseReference
    var r1: RecyclerView? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var madr: RoutesAdapter? = null
    private lateinit var routesList: ArrayList<Routes>
    private lateinit var btnsaveroute:Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        routeView = inflater.inflate(R.layout.fragment_routes_list, container, false)

        routesList = ArrayList<Routes>()
        r1 = routeView.findViewById<RecyclerView>(R.id.routeListRecyclerView)
        layoutManager = LinearLayoutManager(routeView.context)
        r1?.layoutManager = layoutManager

            loadData()

        return routeView
    }

    private fun loadData() {
        reference = FirebaseDatabase.getInstance().getReference("Routes")
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                //val routes = dataSnapshot.getValue(routes::class.java)
                routesList.clear()
                for (dss in dataSnapshot.children) {
                    val code = dss.child("routeCode").getValue().toString()
                    val from = dss.child("routefrom").getValue().toString()
                    val to = dss.child("routeTo").getValue().toString()
                    val route=Routes(dss.key.toString(),code,from,to,"","","","")

                    //val route = dataSnapshot.getValue(Routes::class.java)
                    if(route!=null)
                        routesList.add(route)
                    // Toast.makeText(routeView.context, "This is emito" + route.toString(), Toast.LENGTH_SHORT).show()
                }


                madr = RoutesAdapter(routeView.context, routesList)
                r1?.adapter = madr

                //for the total routes
                var totalroutesTextView: TextView =
                    routeView.findViewById<TextView>(R.id.totalroutesTextView)
                totalroutesTextView.text = madr!!.itemCount.toString()
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
