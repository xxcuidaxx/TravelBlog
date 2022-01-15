package com.example.travelblog_loginscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController

private lateinit var appBarConfig: AppBarConfiguration

class NavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val navctlr = findNavController(R.id.my_nav_host_fragment)
        appBarConfig = AppBarConfiguration(navctlr .graph)
        setupActionBarWithNavController(navctlr, appBarConfig)
    }
        override fun onSupportNavigateUp(): Boolean {
            val navctlr = findNavController(R.id.my_nav_host_fragment)
            return navctlr.navigateUp(appBarConfig) || super.onSupportNavigateUp()
        }


//    class SharedViewModel : ViewModel() {
//        var userEmailAddr = MutableLiveData<String>()
//        var userID = MutableLiveData<String>()
//        val userId get() = userID
//        val userEmail get() = userEmailAddr
//    }


}