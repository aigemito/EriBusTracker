package com.emito.eribus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_account.*

class createAccount : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
    }
    //region Init Functions
    private fun initActions() {
        helpButton.setOnClickListener {
            Toast.makeText(applicationContext, "Clicked Need Help?", Toast.LENGTH_SHORT).show()
        }

        loginButton.setOnClickListener {
            Toast.makeText(applicationContext, "Clicked Login", Toast.LENGTH_SHORT).show()
        }

        registerButton.setOnClickListener {
            finish()
            //Toast.makeText(applicationContext, "Clicked Register", Toast.LENGTH_SHORT).show()
        }
    }
    //endregion
}
