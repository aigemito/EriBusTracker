package com.emito.eribus.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.emito.eribus.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AddBusRouteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_bus_route)
        val ref= FirebaseDatabase.getInstance().getReference("Bus")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) { // Is better to use a List, because you don't know the size
// of the iterator returned by dataSnapshot.getChildren() to
// initialize the array
                val buses: MutableList<String> =
                    ArrayList()
                for (areaSnapshot in dataSnapshot.children) {
                    val areaName =
                        areaSnapshot.child("busPlateNumber").getValue(
                            String::class.java
                        )!!
                    buses.add(areaName)
                }
                val busSpinner = findViewById(R.id.spinner) as Spinner
                val busAdapter = ArrayAdapter(
                    this@AddBusRouteActivity,
                    android.R.layout.simple_spinner_item,
                    buses
                )
                busAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                busSpinner.adapter = busAdapter
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }
}
