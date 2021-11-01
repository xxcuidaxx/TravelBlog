package com.example.travelblog_loginscreen

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern

private val EMAILREGEX =
    Pattern.compile("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}", Pattern.CASE_INSENSITIVE)


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText>(R.id.Email)
        val password = findViewById<EditText>(R.id.Password)
        val loginbutton = findViewById<Button>(R.id.Login)
        val regist = findViewById<Button>(R.id.button_Register)
        val forpass = findViewById<TextView>(R.id.fpass)
        val rem = findViewById<TextView>(R.id.loginrem)
        var loginrem = 5


        rem.setText("No. of attempts remaining: 5")


        loginbutton.setOnClickListener {
            val emailStr = email.text.toString()
            val passStr = password.text.toString()
            var seconds = 60
            val shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            when {
                emailStr.length == 0 ->{
                    loginrem--;
                    rem.setText("No. of attempts remaining: " + loginrem.toString())
                    if (loginrem == 0){
                        loginbutton.isEnabled = false;
                    }
                    Snackbar.make(email, "Email address needed",Snackbar.LENGTH_SHORT).show()
                    loginbutton.startAnimation (shake);}
                !EMAILREGEX.matcher(emailStr).find() ->{
                    loginrem--;
                    rem.setText("No. of attempts remaining: " + loginrem.toString())
                    if (loginrem == 0){
                        loginbutton.isEnabled = false;
                    }
                    Snackbar.make(email,"Enter a valid email address",Snackbar.LENGTH_LONG).show()
                    loginbutton.startAnimation (shake);
                    }
                passStr != "admin123" ->{
                    loginrem--;
                    rem.setText("No. of attempts remaining: " + loginrem.toString())
                    if (loginrem == 0){
                        loginbutton.isEnabled = false;
                    }

                    Snackbar.make(password, "Incorrect Password",Snackbar.LENGTH_LONG).show()
                    loginbutton.startAnimation (shake);
            } else ->
                Snackbar.make(email,"Login successfull", Snackbar.LENGTH_SHORT).show()
                }
        }

        regist.setOnClickListener {
            val intent = Intent(this, Register_Activity::class.java)
            startActivity(intent)
        }

        forpass.setOnClickListener{
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)

        }

    }



}


