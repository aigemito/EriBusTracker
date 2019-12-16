package com.emito.eribus.activity

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.emito.eribus.R
import com.emito.eribus.adapter.AppECommerceUsers2Adapter
import com.emito.eribus.model.Users
//import com.panaceasoft.pskotlinmaterial.R
//import com.panaceasoft.pskotlinmaterial.`object`.Users
//import com.panaceasoft.pskotlinmaterial.adapter.application.ecommerce.Users.AppECommerceUsers2Adapter
//import com.panaceasoft.pskotlinmaterial.repository.ecommerce.UsersItemRepository
//import kotlinx.android.synthetic.main.app_ecommerce_Users_2_activity.*
import kotlinx.android.synthetic.main.route_activity.*

class AppECommerceUsers2Activity : AppCompatActivity() {

    // data and adapter
    internal lateinit var UsersList: List<Users>
    internal lateinit var adapter: AppECommerceUsers2Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.route_detail_activity)

        initData()

        initUI()

        initDataBindings()

        initActions()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initData() {
        // get place list
        //UsersList = UsersItemRepository.busketItemList
    }

    private fun initUI() {
        //initToolbar()

        // get list adapter
        adapter = AppECommerceUsers2Adapter(UsersList)

        // get recycler view
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

    }

    private fun initDataBindings() {
        // bind adapter to recycler
        recyclerView.adapter = adapter

        try {
//            var total = 0
//            for (i in UsersList.indices) {
//                val Users = UsersList[i]
//                total += Integer.parseInt(Users.price)
//            }

           // val totalCost = UsersList[0].currency + " " + total.toString()
           // totalPriceTextView.text = totalCost
        } catch (ignored: Exception) {
        }

    }

    private fun initActions() {
        adapter.setOnItemClickListener(object : AppECommerceUsers2Adapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: Users, position: Int) {
                //Toast.makeText(applicationContext, "Clicked " + obj.userName, Toast.LENGTH_SHORT).show()
            }

            override fun onDeleteClick(view: View, obj: Users, position: Int) {
                Toast.makeText(applicationContext, "Clicked Delete. ", Toast.LENGTH_SHORT).show()
            }

            override fun onPriceChange(currency: String, subTotal: Int) {
                val totalStr = "$currency $subTotal"
                totalPriceTextView.text = totalStr
            }
        })

        checkoutButton.setOnClickListener { Toast.makeText(this, "Clicked Checkout.", Toast.LENGTH_SHORT).show() }
    }

}
