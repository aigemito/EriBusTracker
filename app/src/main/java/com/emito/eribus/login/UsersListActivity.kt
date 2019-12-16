package com.emito.eribus.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emito.eribus.R
import com.emito.eribus.adapter.UserProfileAdapter
import com.emito.eribus.model.Users
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.route_activity.*

class UsersListActivity : AppCompatActivity() {

    private lateinit var reference: DatabaseReference
    var r1: RecyclerView?=null
    var layoutManager: RecyclerView.LayoutManager?=null
    var madr : UserProfileAdapter?=null
    private lateinit var usersList: ArrayList<Users>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)

        usersList=ArrayList<Users>()
        r1 = findViewById<RecyclerView>(R.id.recyclerView)
        layoutManager = LinearLayoutManager(this)
        r1?.layoutManager = layoutManager

        reference=FirebaseDatabase.getInstance().reference.child("Users")
        //reference=FirebaseDatabase.getInstance().getReference().child(user.uid)
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                //val users = dataSnapshot.getValue(Users::class.java)
               for(dss in dataSnapshot.children){
                   val user=dss.getValue(Users::class.java)
                   if (user!=null){
                       usersList.add(user)
                   }

               }

                madr = UserProfileAdapter(this@UsersListActivity,usersList)
                r1?.adapter = madr
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
