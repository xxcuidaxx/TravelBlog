package com.example.travelblog_loginscreen

import android.app.ProgressDialog.show
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern

private val EMAILREGEX =
    Pattern.compile("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}", Pattern.CASE_INSENSITIVE)



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
            val regemailstr = regemail.text.toString()
            val regpassstr = regpassword.text.toString()
            val regconfpassstr = regconfpassword.text.toString()
            val shake = AnimationUtils.loadAnimation(this, R.anim.shake);


            when {
                regemailstr.length == 0 ->{
                    Snackbar.make(regemail, "Email address needed",Snackbar.LENGTH_LONG).show()
                    reg.startAnimation (shake);}
                !EMAILREGEX.matcher(regemailstr).find() ->{
                    Snackbar.make(regemail,"Enter a valid email address",Snackbar.LENGTH_LONG).show()
                    reg.startAnimation (shake); }
                regconfpassstr != regpassstr ->{
                    Snackbar.make(regpassword, "Password do not match",Snackbar.LENGTH_LONG).show()
                    regconfpassword.setText("")
                    reg.startAnimation (shake);}
                regpassword.text.isEmpty() ->{
                    Toast.makeText(this, "Please fill up the form!", Toast.LENGTH_SHORT).show();}
                regconfpassword.text.isEmpty() ->{
                    Toast.makeText(this, "Please fill up the form!", Toast.LENGTH_SHORT).show(); }
                else->
                    Toast.makeText(this, "Registered!", Toast.LENGTH_LONG).show();
            }
            if (regemail.text.trim().isEmpty()){
                Toast.makeText(this, "Please fill up the form!", Toast.LENGTH_SHORT).show(); }
            else if (regpassword.text.isEmpty()){
                Toast.makeText(this, "Please fill up the form!", Toast.LENGTH_SHORT).show(); }
            else if (regconfpassword.text.isEmpty()){
                Toast.makeText(this, "Please fill up the form!", Toast.LENGTH_SHORT).show(); }
            else
                regemail.setText("")
                regpassword.setText("")
                regconfpassword.setText("")

        }

        reglogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}