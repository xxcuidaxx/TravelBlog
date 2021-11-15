package com.example.travelblog_loginscreen

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.travelblog_loginscreen.databinding.ActivityRegister2Binding
import java.util.regex.Pattern
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

private val EMAILREGEX =
    Pattern.compile("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}", Pattern.CASE_INSENSITIVE)

class RegisterActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityRegister2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        binding = ActivityRegister2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_register)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_register)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}