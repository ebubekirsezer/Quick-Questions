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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

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

        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}