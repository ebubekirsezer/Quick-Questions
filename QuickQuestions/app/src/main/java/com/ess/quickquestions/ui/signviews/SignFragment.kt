package com.ess.quickquestions.ui.signviews

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ess.quickquestions.R
import kotlinx.android.synthetic.main.fragment_sign.*

class SignFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onTabSignClick()

        super.onViewCreated(view, savedInstanceState)
    }

    //Sign In and Sign Up tab click events
   private fun onTabSignClick(){
       button_sign_in.setOnClickListener {
           button_sign_in.setTextColor(resources.getColor(R.color.textColorPrimary))
           horizontal_sign_in_line.setBackgroundColor(resources.getColor(R.color.textColorPrimary))
           button_sign_up.setTextColor(resources.getColor(R.color.textGreyColor))
           horizontal_sign_up_line.setBackgroundColor(resources.getColor(R.color.textGreyColor))

           linear_layout_sign_in.visibility = View.VISIBLE
           linear_layout_sign_up.visibility = View.GONE
       }

       button_sign_up.setOnClickListener {
           button_sign_in.setTextColor(resources.getColor(R.color.textGreyColor))
           horizontal_sign_in_line.setBackgroundColor(resources.getColor(R.color.textGreyColor))
           button_sign_up.setTextColor(resources.getColor(R.color.textColorPrimary))
           horizontal_sign_up_line.setBackgroundColor(resources.getColor(R.color.textColorPrimary))

           linear_layout_sign_in.visibility = View.GONE
           linear_layout_sign_up.visibility = View.VISIBLE
       }
   }
}