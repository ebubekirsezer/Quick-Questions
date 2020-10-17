package com.ess.quickquestions.ui.finishview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ess.quickquestions.R
import com.ess.quickquestions.databinding.FragmentFinishBinding

class FinishFragment : Fragment() {

    private lateinit var viewModel: FinishViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.title = ""

        val binding = FragmentFinishBinding.inflate(inflater)
        val score = FinishFragmentArgs.fromBundle(requireArguments()).score
        println(score)

        viewModel = ViewModelProvider(this).get(FinishViewModel::class.java)

        binding.viewModel = viewModel

        return binding.root
    }

}