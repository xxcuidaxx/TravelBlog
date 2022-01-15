package com.example.travelblog_loginscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var userEmailAddr = MutableLiveData<String>()
    var userID = MutableLiveData<String>()
    val userId get() = userID
    val userEmail get() = userEmailAddr
}