package com.example.travelblog_loginscreen

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.travelblog_loginscreen.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val fabforpass = findViewById<FloatingActionButton>(R.id.forgot_pass)
        val fabregist = findViewById<FloatingActionButton>(R.id.registmee)


        val payload = intent
        val userId = findViewById<TextView>(R.id.tv_emailadd)
        if (payload.hasExtra("EMAIL")) {userId.text = payload.getStringExtra("EMAIL")}


//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            fabforpass.visibility = View.VISIBLE
            fabregist.visibility = View.VISIBLE
//            val intent = Intent(this, Register_Activity::class.java)
//            startActivity(intent)
        }
        fabforpass.setOnClickListener {
            val intent = Intent(this@MainActivity, ForgotPasswordActivity::class.java)
            startActivity(intent)
            finish()
        }
        fabregist.setOnClickListener {
            val intent = Intent(this@MainActivity, Register_Activity::class.java)
            startActivity(intent)
            finish()
        }

    }
//    private fun onAddButtonClicked(){
//        setVisibility(clicked)
//        clicked = !clicked
//    }
//
//    private fun setVisibility(clicked: Boolean) {
//        if(!clicked){
//            fabforpass.visibility = View.VISIBLE
//            fabregist.visibility = View.VISIBLE
//        } else{
//            fabforpass.visibility = View.INVISIBLE
//            fabregist.visibility = View.INVISIBLE
//        }
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu); return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_logout) {
            finish(); return true }
        return false}

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }



}