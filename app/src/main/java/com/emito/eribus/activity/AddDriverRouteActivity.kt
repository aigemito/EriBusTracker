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

class AddDriverRouteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_driver_route)
        val ref= FirebaseDatabase.getInstance().getReference("Users").orderByChild("userType")
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
                val busSpinner = findViewById(R.id.spinner) as Spinner
                val busAdapter = ArrayAdapter(
                    this@AddDriverRouteActivity,
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
