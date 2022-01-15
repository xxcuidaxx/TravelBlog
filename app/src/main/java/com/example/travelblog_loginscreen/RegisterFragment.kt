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
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern



private val EMAILREGEX =
    Pattern.compile("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}", Pattern.CASE_INSENSITIVE)

class RegisterFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fab: View = view.findViewById(R.id.fab)
        val reglogin = view.findViewById<Button>(R.id.reg_login)
        val regemail = view.findViewById<EditText>(R.id.reg_email)
        val regpassword = view.findViewById<EditText>(R.id.reg_pass)
        val regconfpassword = view.findViewById<EditText>(R.id.reg_confpass)
        val reguserid = view.findViewById<EditText>(R.id.reg_username)


        reglogin.setOnClickListener {
            findNavController().navigate(R.id.action_register_to_login)
        }
        fab.setOnClickListener { view ->
            val reguseridstr = reguserid.text.toString()
            val regemailstr = regemail.text.toString()
            val regpassstr = regpassword.text.toString()
            val regconfpassstr = regconfpassword.text.toString()
            val shake = AnimationUtils.loadAnimation(this.context, R.anim.shake);

            viewModel.userEmail.value = regemailstr
            viewModel.userId.value = "Hi $reguseridstr, Good day!"


            when {
                reguseridstr.length == 0 ->{
                    Snackbar.make(regemail, "Username needed", Snackbar.LENGTH_LONG).show()
                    fab.startAnimation (shake);}
                regemailstr.length == 0 ->{
                    Snackbar.make(regemail, "Email address needed", Snackbar.LENGTH_LONG).show()
                    fab.startAnimation (shake);}
                !EMAILREGEX.matcher(regemailstr).find() ->{
                    Snackbar.make(regemail,"Enter a valid email address", Snackbar.LENGTH_LONG).show()
                    fab.startAnimation (shake); }
                regconfpassstr != regpassstr ->{
                    Snackbar.make(regpassword, "Password do not match", Snackbar.LENGTH_LONG).show()
                    regconfpassword.setText("")
                    fab.startAnimation (shake);}
                regpassword.text.isEmpty() ->{
                    Toast.makeText(this.context, "Please fill up the form!", Toast.LENGTH_SHORT).show();}
                regconfpassword.text.isEmpty() ->{
                    Toast.makeText(this.context, "Please fill up the form!", Toast.LENGTH_SHORT).show(); }
                else ->{
                    Toast.makeText(this.context, "Registered!", Toast.LENGTH_LONG).show();
                    reguserid.setText("")
                    regemail.setText("")
                    regpassword.setText("")
                    regconfpassword.setText("")
                    findNavController().navigate(R.id.action_register_to_main)
                }
            }
//            if (regemail.text.trim().isEmpty()){
//                Toast.makeText(this.context, "Please fill up the form!", Toast.LENGTH_SHORT).show(); }
//            else if (regpassword.text.isEmpty()){
//                Toast.makeText(this.context, "Please fill up the form!", Toast.LENGTH_SHORT).show(); }
//            else if (regconfpassword.text.isEmpty()){
//                Toast.makeText(this.context, "Please fill up the form!", Toast.LENGTH_SHORT).show(); }
//            else{
//                regemail.setText("")
//                regpassword.setText("")
//                regconfpassword.setText("")
//                findNavController().navigate(R.id.action_register_to_main, bundleOf("String" to regemailstr))
//            }

        }

    }






}