package com.example.travelblog_loginscreen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern



private val EMAILREGEX =
    Pattern.compile("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}", Pattern.CASE_INSENSITIVE)


class LoginFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_login, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userid = view.findViewById<EditText>(R.id.userid)
        val email = view.findViewById<EditText>(R.id.Email)
        val password = view.findViewById<EditText>(R.id.Password)
        val loginbutton = view.findViewById<Button>(R.id.Login)
        val regist = view.findViewById<Button>(R.id.button_Register)
        val forpass = view.findViewById<TextView>(R.id.fpass)
        val rem = view.findViewById<TextView>(R.id.loginrem)
        var loginrem = 5



        rem.setText("No. of attempts remaining: 5")

        loginbutton.setOnClickListener {
            val useridstr = userid.text.toString()
            val emailStr = email.text.toString()
            val passStr = password.text.toString()
            viewModel.userEmail.value = emailStr
            viewModel.userId.value = "Hi $useridstr, Good day!"

            val shake = AnimationUtils.loadAnimation(this.context, R.anim.shake);

            when {
                useridstr.length == 0 -> {
                    loginrem--;
                    rem.setText("No. of attempts remaining: " + loginrem.toString())
                    Snackbar.make(email, "Username needed",Snackbar.LENGTH_SHORT).show()
                    loginbutton.startAnimation (shake)
                }
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
                } else -> {
                Snackbar.make(email, "Login successfull", Snackbar.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_login_to_main)

                }

            }

        }
        regist.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }

        forpass.setOnClickListener{
            findNavController().navigate(R.id.action_login_to_forgot)
        }
    }




}