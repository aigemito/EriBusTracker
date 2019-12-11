package com.emito.eribus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initActions()
    }

    //endregion


    private fun initActions() {
        needHelpTextView.setOnClickListener {
            Toast.makeText(applicationContext, "Clicked Need Help?", Toast.LENGTH_SHORT).show()
        }

        createAccountButton.setOnClickListener {
            var intent=Intent(this,createAccount::class.java)
            startActivity(intent)
            //Toast.makeText(applicationContext, "Clicked Create Account", Toast.LENGTH_SHORT).show()
        }

        forgotButton.setOnClickListener {
            var intent=Intent(this,ForgotPassword::class.java)
            startActivity(intent)
            //Toast.makeText(applicationContext, "Clicked Forgot Password?", Toast.LENGTH_SHORT).show()
        }

        loginButton.setOnClickListener {
            Toast.makeText(applicationContext, "Clicked Login", Toast.LENGTH_SHORT).show()
        }
    }
    //endregion
}
