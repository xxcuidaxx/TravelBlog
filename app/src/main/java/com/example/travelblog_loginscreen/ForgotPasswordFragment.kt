package com.example.travelblog_loginscreen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern


private val EMAILREGEX =
    Pattern.compile("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}", Pattern.CASE_INSENSITIVE)


class ForgotPasswordFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_forgot_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fuserid = view.findViewById<EditText>(R.id.fuserid)
        val foremail = view.findViewById<EditText>(R.id.femail)
        val forpassword = view.findViewById<EditText>(R.id.fnewpass)
        val resetpass = view.findViewById<Button>(R.id.reset_button)
        val forlog = view.findViewById<Button>(R.id.forlogin)
        forpassword.isEnabled = false
        resetpass.setText("Check email")


        resetpass.setOnClickListener {

            val foremailstr = foremail.text.toString()
            val forpassstr = forpassword.text.toString()
            val foruseridstr = fuserid.text.toString()

            viewModel.userEmail.value = foremailstr
            viewModel.userId.value = "Hi $foruseridstr, Good day!"

            if (foremailstr.length == 0) {
                Snackbar.make(foremail, "Email address needed", Snackbar.LENGTH_LONG).show()
            } else if (!EMAILREGEX.matcher(foremailstr).find()) {
                Snackbar.make(foremail, "Enter a valid email address", Snackbar.LENGTH_LONG).show()
            } else if (foruseridstr.length == 0){
                Snackbar.make(foremail, "Username needed", Snackbar.LENGTH_LONG).show()
            }
            else{
                resetpass.setText("Reset Password")
                forpassword.isEnabled = true
                if (forpassstr.length == 0) {
                    Snackbar.make(foremail, "Please enter a password", Snackbar.LENGTH_LONG).show() }
                else {
                    Snackbar.make(foremail, "Password has been reset successfully", Snackbar.LENGTH_LONG).show()
                    fuserid.setText("")
                    foremail.setText("")
                    forpassword.setText("")
                    findNavController().navigate(R.id.action_forgot_to_main)
                }
            }
        }

        forlog.setOnClickListener {
            findNavController().navigate(R.id.action_forgot_to_login)
        }


    }


}