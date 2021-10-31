package com.example.travelblog_loginscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class Register_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val reglogin = findViewById<Button>(R.id.reg_login)
        val reg = findViewById<Button>(R.id.button_reg)
        val regemail = findViewById<EditText>(R.id.reg_email)
        val regpassword = findViewById<EditText>(R.id.reg_pass)
        val regconfpassword = findViewById<EditText>(R.id.reg_confpass)


        reg.setOnClickListener {
            if (regemail.text.trim().isNotEmpty() || regpassword.text.isNotEmpty() || regconfpassword.text.isNotEmpty()){
                Toast.makeText(this, "Registered!", Toast.LENGTH_LONG).show()
                regemail.setText("")
                regpassword.setText("")
                regconfpassword.setText("")
            } else{
                Toast.makeText(this, "Please fill up the form!", Toast.LENGTH_LONG).show()
            }


        }

        reglogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }
}