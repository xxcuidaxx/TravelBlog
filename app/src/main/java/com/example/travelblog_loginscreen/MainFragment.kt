package com.example.travelblog_loginscreen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainFragment : Fragment() {

    lateinit var emailAddr: String

    val viewModel by activityViewModels<SharedViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val fabforpass = view?.findViewById<FloatingActionButton>(R.id.forgot_pass)
        val fabregist = view?.findViewById<FloatingActionButton>(R.id.registmee)
        val fab = view?.findViewById<FloatingActionButton>(R.id.fab)
        val useremail = view?.findViewById<TextView>(R.id.tvemailadd)
        val userid = view?.findViewById<TextView>(R.id.tvuserid)

        viewModel.userID.observe(this.viewLifecycleOwner, Observer { z ->
            userid?.text = z})
        viewModel.userEmail.observe(this.viewLifecycleOwner, Observer { z ->
            useremail?.text = z})


        if (fab != null) {
            fab.setOnClickListener { view ->
                if (fabforpass != null) {
                    fabforpass.visibility = View.VISIBLE
                }
                if (fabregist != null) {
                    fabregist.visibility = View.VISIBLE
                }
            }
        }
        if (fabforpass != null) {
            fabforpass.setOnClickListener {
                val viewSiteIntent= Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tupcavite.edu.ph//"))
                startActivity(viewSiteIntent)
            }
        }
        if (fabregist != null) {
            fabregist.setOnClickListener {
                findNavController().navigate(R.id.action_main_to_camera)
            }
        }



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        emailAddr = requireArguments().getString("String").toString()

        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_main, container, false)
    }






    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_logout) {
            findNavController().navigate(R.id.action_main_to_login)
            return true
        }
        return false }
    }



