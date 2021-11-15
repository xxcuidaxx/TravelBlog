package com.example.travelblog_loginscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern


private val EMAILREGEX =
    Pattern.compile("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}", Pattern.CASE_INSENSITIVE)

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val foremail = findViewById<EditText>(R.id.femail)
        val forpassword = findViewById<EditText>(R.id.fnewpass)
        val resetpass = findViewById<Button>(R.id.reset_button)
        val forlog = findViewById<Button>(R.id.forlogin)
        forpassword.isEnabled = false
        resetpass.setText("Check email")


        resetpass.setOnClickListener {

            val foremailstr = foremail.text.toString()
            val forpassstr = forpassword.text.toString()

            if (foremailstr.length == 0) {
                Snackbar.make(foremail, "Email address needed", Snackbar.LENGTH_LONG).show()
            } else if (!EMAILREGEX.matcher(foremailstr).find()) {
                Snackbar.make(foremail, "Enter a valid email address", Snackbar.LENGTH_LONG).show()
            } else{
                resetpass.setText("Reset Password")
                forpassword.isEnabled = true
                if (forpassstr.length == 0) {
                    Snackbar.make(foremail, "Please enter a password", Snackbar.LENGTH_LONG).show() }
                else {
                    Snackbar.make(foremail, "Password has been reset successfully", Snackbar.LENGTH_LONG).show()
                    foremail.setText("")
                    forpassword.setText("")
                    val mainIntent = Intent(this@ForgotPasswordActivity, MainActivity::class.java)
                    mainIntent.putExtra("EMAIL", foremailstr)
                    startActivity(mainIntent)
                    finish()
                }


            }

        }

        forlog.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }
}
