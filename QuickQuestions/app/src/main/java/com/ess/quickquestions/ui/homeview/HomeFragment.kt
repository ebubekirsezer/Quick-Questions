package com.ess.quickquestions.ui.homeview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.ess.quickquestions.R
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val homeView =  inflater.inflate(R.layout.fragment_home, container, false)

        (activity as AppCompatActivity).setSupportActionBar(homeView.findViewById(R.id.home_toolbar))
        // Inflate the layout for this fragment
        return homeView
    }
}